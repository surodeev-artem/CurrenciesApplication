package com.surodeev.currenciestestapplication.entity

sealed class CurrenciesState {

    data class Success(
        val timestamp: Long,
        val currencies: List<Currency>,
        val favoritesCurrencies: List<Currency>
    ) : CurrenciesState()

    data class Failed(
        val message: String
    ) : CurrenciesState()

    object Initial : CurrenciesState()
}
