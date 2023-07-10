package com.bunooboi.stadice

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bunooboi.stadice.database.RoomApplication
import com.bunooboi.stadice.database.RoomApplication.Companion.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.max

class AppViewModel : ViewModel() {
    private val dao = RoomApplication.database.taskDao()
    var tasks: LiveData<MutableList<Task>> = dao.getAll()

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