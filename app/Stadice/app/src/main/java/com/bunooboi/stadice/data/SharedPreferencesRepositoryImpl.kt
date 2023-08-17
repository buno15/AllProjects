package com.bunooboi.stadice.data

import android.content.SharedPreferences
import com.bunooboi.stadice.RandomTime
import com.bunooboi.stadice.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) : SharedPreferencesRepository {
    private val KEY_ID = "id"
    private val KEY_NAME = "name"
    private val KEY_FINISHED = "finished"
    private val KEY_DATE = "date"
    private val RANDOM_HOUR = "randomHour"
    private val RANDOM_MINUTE = "randomMinute"

    override suspend fun savePriorityTask(task: Task) {
        withContext(Dispatchers.Default) {
            val editor = sharedPreferences.edit()
            editor.putInt(KEY_ID, task.id)
            editor.putString(KEY_NAME, task.name)
            editor.putBoolean(KEY_FINISHED, task.finished)
            editor.putLong(KEY_DATE, task.date.time)
            editor.apply()
        }
    }

    override suspend fun loadPriorityTask(): Task {
        return withContext(Dispatchers.Default) {
            val id = sharedPreferences.getInt(KEY_ID, -1)
            val name = sharedPreferences.getString(KEY_NAME, "")
            val finished = sharedPreferences.getBoolean(KEY_FINISHED, false)
            val date = Date(sharedPreferences.getLong(KEY_DATE, 0))
            Task(id, name!!, finished, date)
        }
    }

    override suspend fun saveRandomTime(time: RandomTime) {
        withContext(Dispatchers.Default) {
            val editor = sharedPreferences.edit()
            editor.putInt(RANDOM_HOUR, time.hour)
            editor.putInt(RANDOM_MINUTE, time.minute)
            editor.apply()
        }
    }

    override suspend fun loadRandomTime(): RandomTime {
        return withContext(Dispatchers.Default) {
            val randomHour = sharedPreferences.getInt(RANDOM_HOUR, 9)
            val randomMinute = sharedPreferences.getInt(RANDOM_MINUTE, 0)
            RandomTime(randomHour, randomMinute)
        }
    }
}