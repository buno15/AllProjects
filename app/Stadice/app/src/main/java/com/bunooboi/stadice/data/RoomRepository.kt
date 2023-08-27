package com.bunooboi.stadice.data

import com.bunooboi.stadice.Task

interface RoomRepository {
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun selectTasks(): List<Task>
}