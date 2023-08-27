package com.bunooboi.stadice.di

import com.bunooboi.stadice.data.RoomRepository
import com.bunooboi.stadice.data.RoomRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RoomModule {
    @Binds
    @Singleton
    abstract fun bindRoomRepositoryImpl(impl: RoomRepositoryImpl): RoomRepository
}*/