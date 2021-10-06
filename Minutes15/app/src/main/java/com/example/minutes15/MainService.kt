package com.example.minutes15

import android.R
import android.R.id
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.util.*


class MainService : Service() {
    var timer: Timer? = null
    var handler: Handler = Handler()
    var nm: NotificationManager? = null
    var builder: NotificationCompat.Builder? = null
    var timeCount = 0
    var id = "Run"
    var name = "Minutes15"

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val mChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
        nm = getSystemService(NotificationManager::class.java)
        nm!!.createNotificationChannel(mChannel)
        builder = NotificationCompat.Builder(applicationContext, id)
        builder!!.setSmallIcon(R.drawable.icon)
        builder!!.setContentTitle("Minutes15")
        builder!!.setContentText("Start Minutes15")
        builder!!.setAutoCancel(true)
        val note: Notification = builder!!.build()
        note.flags = note.flags or Notification.FLAG_AUTO_CANCEL
        startForeground(2, note)
        Toast.makeText(applicationContext, "Start Minutes15", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Runnable {
                    if (++timeCount % 90 === 0) {
                    }
                })
            }
        }, 0, 10000)
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        super.onDestroy()
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
        nm!!.cancel(1)
        stopForeground(STOP_FOREGROUND_DETACH)
        val nm = getSystemService(NotificationManager::class.java)
        nm.deleteNotificationChannel(id)
        Toast.makeText(applicationContext, "Stop Minutes15", Toast.LENGTH_SHORT).show()
    }
}