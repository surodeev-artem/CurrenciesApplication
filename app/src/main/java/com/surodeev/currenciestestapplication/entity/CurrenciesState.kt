package com.surodeev.currenciestestapplication.entity

sealed class CurrenciesState {

    data class Success(
        val timestamp: Long,
        val currencies: List<Currency>
    ) : CurrenciesState() {

        fun copyWith(
            timestamp: Long = this.timestamp,
            currencies: List<Currency> = this.currencies
        ): Success {
            return Success(timestamp, currencies)
        }
    }

    data class Failed(
        val message: String
    ) : CurrenciesState()

    object Initial : CurrenciesState()
}
