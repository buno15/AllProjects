package com.bunooboi.stadice

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bunooboi.stadice.data.RoomRepository
import com.bunooboi.stadice.data.RoomRepositoryImpl
import com.bunooboi.stadice.database.AppDatabase
import com.bunooboi.stadice.database.TaskDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class RoomRepositoryTest {
    lateinit var roomRepository: RoomRepository

    private lateinit var db: AppDatabase
    private lateinit var taskDao: TaskDao

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    @ExperimentalCoroutinesApi
    @Before
    fun createRepository() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        taskDao = db.taskDao()
        roomRepository = RoomRepositoryImpl(taskDao)
    }


    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertTask() = runTest {
        val newTask = Task(-1, "asdf", false, Date())
        roomRepository.insertTask(newTask)

        val retrievedUser = roomRepository.selectTasks()
        Assert.assertNotNull(retrievedUser[0])
        Assert.assertEquals(newTask.id, retrievedUser[0].id)
        Assert.assertEquals(newTask.name, retrievedUser[0].name)
        Assert.assertEquals(newTask.finished, retrievedUser[0].finished)
        Assert.assertEquals(newTask.date, retrievedUser[0].date)
    }

    @Test
    fun getTasks() = testScope.runTest {
        val tasks = roomRepository.selectTasks()
    }
}