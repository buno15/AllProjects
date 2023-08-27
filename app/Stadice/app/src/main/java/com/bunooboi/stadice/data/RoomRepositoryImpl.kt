package com.bunooboi.stadice.data

import com.bunooboi.stadice.Task
import com.bunooboi.stadice.database.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val dao: TaskDao) : RoomRepository {
    private val accessMutex = Mutex()

    override suspend fun insertTask(task: Task) {
        withContext(Dispatchers.IO) { dao.insert(task) }
    }

    override suspend fun updateTask(task: Task) {
        withContext(Dispatchers.IO) { dao.update(task) }
    }

    override suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) { dao.delete(task) }
    }

    override suspend fun selectTasks(): List<Task> = accessMutex.withLock {
        return withContext(Dispatchers.IO) {
            dao.getAll()
        }
    }
}