package com.bunooboi.stadice.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.bunooboi.stadice.MainApplication
import com.bunooboi.stadice.Task
import com.bunooboi.stadice.data.RoomRepository
import com.bunooboi.stadice.data.RoomRepositoryImpl
import com.bunooboi.stadice.data.SharedPreferencesRepository
import com.bunooboi.stadice.data.SharedPreferencesRepositoryImpl
import com.bunooboi.stadice.database.AppDatabase
import com.bunooboi.stadice.database.TaskDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Stadice", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesRepository(sharedPreferences: SharedPreferences): SharedPreferencesRepository {
        return SharedPreferencesRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "tasks").build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao()

    @Provides
    @Singleton
    fun provideRoomRepository(taskDao: TaskDao): RoomRepository {
        return RoomRepositoryImpl(taskDao)
    }
}