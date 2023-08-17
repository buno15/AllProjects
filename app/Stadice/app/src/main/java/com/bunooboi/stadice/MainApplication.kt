package com.bunooboi.stadice

import android.app.Application
import androidx.room.Room
import com.bunooboi.stadice.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "tasks").build()
    }
}