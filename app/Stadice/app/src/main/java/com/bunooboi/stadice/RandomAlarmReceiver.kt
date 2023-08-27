package com.bunooboi.stadice

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bunooboi.stadice.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class RandomAlarmReceiver : BroadcastReceiver() {
    private var tasks: MutableList<Task> = mutableListOf()
    var priorityTask = Task(-1, "", false, Date())

    var randomTime: RandomTime = RandomTime(9, 0)

    override fun onReceive(context: Context, intent: Intent) {
        loadRandomTime(context)
        setAlarm(context)

        val dao = MainApplication.database.taskDao()
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                tasks = dao.getAllNotLive()

                setPriorityTaskRandom(context)
                createNotification(context)
            }
        }
    }

    private fun createNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        val notificationBuilder =
            NotificationCompat.Builder(context, "default").setSmallIcon(R.drawable.icon).setContentTitle(if (priorityTask.id != -1) "今日は ${priorityTask.name} を取り組みましょう" else "タスクが登録されていません").setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun setAlarm(context: Context) {
        val hour = randomTime.hour
        val minute = randomTime.minute

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()

            if (get(Calendar.HOUR_OF_DAY) >= hour && get(Calendar.MINUTE) >= minute) {
                add(Calendar.DATE, 1)
            }

            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, RandomAlarmReceiver::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun setPriorityTaskRandom(context: Context) {
        val unfinishedTasks = tasks.filter { !it.finished }
        if (unfinishedTasks.isNotEmpty()) {
            val randomTaskIndex: Int = Math.floor(Math.random() * unfinishedTasks.size).toInt()
            priorityTask = unfinishedTasks[randomTaskIndex].copy()
        }
        savePriorityTask(priorityTask, context)
    }

    private fun savePriorityTask(task: Task, context: Context) {
        val sharedPreferences = context.getSharedPreferences("Stadice", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("id", task.id)
        editor.putString("name", task.name)
        editor.putBoolean("finished", task.finished)
        editor.putLong("date", task.date.time)
        editor.apply()
    }

    private fun loadRandomTime(context: Context) {
        val sharedPreferences = context.getSharedPreferences("Stadice", Context.MODE_PRIVATE)
        val randomHour = sharedPreferences.getInt("randomHour", 9)
        val randomMinute = sharedPreferences.getInt("randomMinute", 0)
        randomTime = RandomTime(randomHour, randomMinute)
    }
}