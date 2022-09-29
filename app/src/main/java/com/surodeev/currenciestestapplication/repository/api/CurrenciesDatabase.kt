package com.surodeev.currenciestestapplication.repository.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.surodeev.currenciestestapplication.entity.FavoriteCurrency
import com.surodeev.currenciestestapplication.entity.dao.FavoritesDao

@Database(entities = [FavoriteCurrency::class], version = 1)
abstract class CurrenciesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}