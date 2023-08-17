package com.bunooboi.stadice.di

import android.content.Context
import android.content.SharedPreferences
import com.bunooboi.stadice.data.SharedPreferencesRepository
import com.bunooboi.stadice.data.SharedPreferencesRepositoryImpl
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
}