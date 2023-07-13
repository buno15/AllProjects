package com.bunooboi.stadice

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bunooboi.stadice.database.RoomApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.max

class AppViewModel : ViewModel() {
    private val dao = RoomApplication.database.taskDao()
    var tasks: LiveData<MutableList<Task>> = dao.getAll()

    private val _priorityTask = MutableLiveData<Task>(Task(-1, "", false, Date()))
    val priorityTask: LiveData<Task> = _priorityTask

    private val _randomTime = MutableLiveData<RandomTime>(RandomTime(9, 0))
    val randomTime: LiveData<RandomTime> = _randomTime

    fun setPriorityTaskRandom() {
        val unfinishedTasks = tasks.value!!.filter { !it.finished }
        if (unfinishedTasks.isEmpty()) {
            _priorityTask.value = Task(-1, "", false, Date())
        } else {
            _priorityTask.value = unfinishedTasks.random()
        }
    }

    fun savePriorityTask(task: Task, context: Context) {
        val sharedPreferences = context.getSharedPreferences("Stadice", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("id", task.id)
        editor.putString("name", task.name)
        editor.putBoolean("finished", task.finished)
        editor.putLong("date", task.date.time)
        editor.apply()
    }

    fun loadPriorityTask(context: Context) {
        val sharedPreferences = context.getSharedPreferences("Stadice", Context.MODE_PRIVATE)
        val id = sharedPreferences.getInt("id", -1)
        val name = sharedPreferences.getString("name", "")
        val finished = sharedPreferences.getBoolean("finished", false)
        val date = Date(sharedPreferences.getLong("date", 0))
        _priorityTask.value = Task(id, name!!, finished, date)
    }

    fun saveRandomTime(time: RandomTime, context: Context) {
        val sharedPreferences = context.getSharedPreferences("Stadice", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("randomHour", time.hour)
        editor.putInt("randomMinute", time.minute)
        editor.apply()
    }

    fun loadRandomTime(context: Context) {
        val sharedPreferences = context.getSharedPreferences("Stadice", Context.MODE_PRIVATE)
        val randomHour = sharedPreferences.getInt("randomHour", 9)
        val randomMinute = sharedPreferences.getInt("randomMinute", 0)
        _randomTime.value = RandomTime(randomHour, randomMinute)
    }


    private fun getIdIndex(): Int {
        var max = 0;
        for (task in tasks.value!!) {
            max = max(max, task.id)
        }
        return max + 1
    }

    fun insertTask(name: String) {
        viewModelScope.launch(Dispatchers.IO) { dao.insert(Task(getIdIndex(), name, false, Date())) }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { dao.update(task) }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { dao.delete(task) }
    }
}