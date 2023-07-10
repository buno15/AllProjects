package com.bunooboi.stadice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AppViewModel : ViewModel() {
    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val tasks: LiveData<MutableList<Task>> = _tasks

    init {
        _tasks.value = mutableListOf(Task(0, "Task1", false, Date()), Task(1, "Task2", false, Date()), Task(2, "Task3", false, Date()))
    }
}