package com.surodeev.currenciestestapplication.entity

import com.surodeev.currenciestestapplication.ui.viewmodel.MainViewModel

sealed class CurrenciesState {

    data class Success(
        val timestamp: Long,
        val currencies: List<Currency>,
        val favoritesCurrencies: List<Currency>
    ) : CurrenciesState() {

        fun copyWith(
            timestamp: Long = this.timestamp,
            currencies: List<Currency> = this.currencies,
            favoritesCurrencies: List<Currency> = this.favoritesCurrencies
        ): Success {
            return Success(timestamp, currencies, favoritesCurrencies)
        }

        override fun equals(other: Any?): Boolean {
            return if (other !is Success) {
                false
            } else other.timestamp == timestamp &&
                    other.currencies == currencies &&
                    other.favoritesCurrencies == favoritesCurrencies
        }
    }

    data class Failed(
        val message: String
    ) : CurrenciesState()

    object Initial : CurrenciesState()
}
