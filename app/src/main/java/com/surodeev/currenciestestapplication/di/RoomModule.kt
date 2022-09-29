package com.surodeev.currenciestestapplication.di

import android.content.Context
import androidx.room.Room
import com.surodeev.currenciestestapplication.repository.api.CurrenciesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideCurrenciesDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        CurrenciesDatabase::class.java, "currencies"
    ).build()
}