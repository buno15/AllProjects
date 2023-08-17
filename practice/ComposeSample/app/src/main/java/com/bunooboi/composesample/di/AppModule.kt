package com.bunooboi.composesample.di

import android.content.Context
import android.content.SharedPreferences
import com.bunooboi.composesample.PreferencesRepository
import com.bunooboi.composesample.SharedPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(sharedPreferences: SharedPreferences): PreferencesRepository {
        return SharedPreferencesRepository(sharedPreferences)
    }
}