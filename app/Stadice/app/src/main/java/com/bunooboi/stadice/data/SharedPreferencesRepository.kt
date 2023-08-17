package com.bunooboi.stadice.data

import com.bunooboi.stadice.RandomTime
import com.bunooboi.stadice.Task

interface SharedPreferencesRepository {
    suspend fun savePriorityTask(task: Task)
    suspend fun loadPriorityTask(): Task
    suspend fun saveRandomTime(time: RandomTime)
    suspend fun loadRandomTime(): RandomTime
}