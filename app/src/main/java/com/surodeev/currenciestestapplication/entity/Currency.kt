package com.surodeev.currenciestestapplication.entity

data class Currency(
    var code: String,
    var value: Double,
    var favorite: Boolean = false
)