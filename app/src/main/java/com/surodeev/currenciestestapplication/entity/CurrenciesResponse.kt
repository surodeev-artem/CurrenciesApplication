package com.surodeev.currenciestestapplication.entity

data class CurrenciesResponse (
    val success: Boolean,
    val timestamp: Long,
    val rates: Map<String, Double>
)