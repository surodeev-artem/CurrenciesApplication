package com.surodeev.currenciestestapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteCurrency(
    @PrimaryKey val code: String
)
