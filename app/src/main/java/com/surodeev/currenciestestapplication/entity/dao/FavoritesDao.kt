package com.surodeev.currenciestestapplication.entity.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surodeev.currenciestestapplication.entity.FavoriteCurrency

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    fun getAll(): List<FavoriteCurrency>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrency(currency: FavoriteCurrency)

    @Delete
    fun deleteCurrency(currency: FavoriteCurrency)
}