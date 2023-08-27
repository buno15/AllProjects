package com.bunooboi.stadice

import androidx.lifecycle.*
import com.bunooboi.stadice.data.RoomRepository
import com.bunooboi.stadice.data.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class AppViewModel @Inject constructor(private val preferenceRepository: SharedPreferencesRepository, private val roomRepository: RoomRepository) : ViewModel() {
    var tasks: MutableLiveData<MutableList<Task>> = MutableLiveData(mutableListOf())

    private val _priorityTask = MutableLiveData(Task(-1, "", false, Date()))
    val priorityTask: LiveData<Task> = _priorityTask

    private val _randomTime = MutableLiveData(RandomTime(9, 0))
    val randomTime: LiveData<RandomTime> = _randomTime

    init {
        viewModelScope.launch {}
    }

    fun savePriorityTask(task: Task) {
        viewModelScope.launch {
            preferenceRepository.savePriorityTask(task)
        }
    }

    fun loadPriorityTask() {
        viewModelScope.launch {
            _priorityTask.value = preferenceRepository.loadPriorityTask()
        }
    }

    fun saveRandomTime(time: RandomTime) {
        viewModelScope.launch { preferenceRepository.saveRandomTime(time) }
    }

    fun loadRandomTime() {
        viewModelScope.launch { _randomTime.value = preferenceRepository.loadRandomTime() }
    }


    fun setPriorityTaskRandom() {
        val unfinishedTasks = tasks.value!!.filter { !it.finished }
        if (unfinishedTasks.isEmpty()) {
            _priorityTask.value = Task(-1, "", false, Date())
        } else {
            _priorityTask.value = unfinishedTasks.random()
        }
    }


    private fun getIdIndex(): Int {
        var max = 0;
        for (task in tasks.value!!) {
            max = max(max, task.id)
        }
        return max + 1
    }

    fun insertAndRefreshTask(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTask = Task(getIdIndex(), name, false, Date())
            roomRepository.insertTask(newTask)
            refreshTasks()
        }
    }

    fun updateAndRefreshTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.updateTask(task)
            refreshTasks()
        }
    }

    fun deleteAndRefreshTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.deleteTask(task)
            refreshTasks()
        }
    }

    fun refreshTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = roomRepository.selectTasks().toMutableList()
            tasks.postValue(data)
        }
    }
}