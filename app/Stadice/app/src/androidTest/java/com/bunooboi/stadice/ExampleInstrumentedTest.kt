package com.bunooboi.stadice

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bunooboi.stadice.database.AppDatabase
import com.bunooboi.stadice.database.TaskDao
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var db: AppDatabase
    private lateinit var userDao: TaskDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = db.taskDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertAndRetrieve() = runTest {
        val user = Task(-1, "asdf", false, Date())
        userDao.insert(user)

        val retrievedUser = userDao.getAllNotLive()
        assertNotNull(retrievedUser[0])
        assertEquals(user.id, retrievedUser[0].id)
        assertEquals(user.name, retrievedUser[0].name)
        assertEquals(user.finished, retrievedUser[0].finished)
        assertEquals(user.date, retrievedUser[0].date)
    }

    @Test
    fun testInsertConflict() = runTest {
        val user = Task(-1, "asdf", false, Date())
        userDao.insert(user)
        userDao.insert(user)

        val retrievedUser = userDao.getAllNotLive()
        assertNotNull(retrievedUser[0])
        assertEquals(user.id, retrievedUser[0].id)
        assertEquals(user.name, retrievedUser[0].name)
        assertEquals(user.finished, retrievedUser[0].finished)
        assertEquals(user.date, retrievedUser[0].date)
    }
}