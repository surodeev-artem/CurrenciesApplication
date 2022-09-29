package com.surodeev.currenciestestapplication.ui.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surodeev.currenciestestapplication.domain.CurrenciesInteractor
import com.surodeev.currenciestestapplication.entity.CurrenciesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val interactor: CurrenciesInteractor) : ViewModel() {
    var screenState by mutableStateOf(ScreenState(CurrenciesState.Initial, "EUR", true))
    var sortVariant = FilterViewModel.SortVariant.ALPHABET_ASC
        set(value) {
            field = value
            if (screenState.currenciesState is CurrenciesState.Success) {
                screenState = screenState.copyWith(
                    currenciesState = (screenState.currenciesState as CurrenciesState.Success).copyWith(
                        currencies = interactor.sortRates(
                            (screenState.currenciesState as CurrenciesState.Success).currencies,
                            value
                        )
                    )
                )
            }
        }
    val baseRatesListState = LazyListState()

    init {
        fetchData()
        scrollToBaseCurrency()
    }

    fun fetchData() {
        val baseCurrency = screenState.baseCurrencyCode
        screenState = screenState.copyWith(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val currenciesState = interactor.getCurrencies(
                baseCurrency,
                sortVariant
            )
            withContext(Dispatchers.Main) {
                screenState = screenState.copyWith(currenciesState, loading = false)
            }
        }
    }

    fun changeBaseCurrency(currencyCode: String) {
        screenState = screenState.copyWith(baseCurrencyCode = currencyCode)
        scrollToBaseCurrency()
        fetchData()
    }

    private fun scrollToBaseCurrency() {
        viewModelScope.launch {
            val baseRateIndex = findBaseRateIndexByCode(screenState.baseCurrencyCode)
            if (baseRateIndex != -1) {
                baseRatesListState.scrollToItem(baseRateIndex, -200)
            }
        }
    }

    private suspend fun updateScreenState(
        currenciesState: CurrenciesState = screenState.currenciesState,
        baseCurrencyCode: String = screenState.baseCurrencyCode,
        loading: Boolean = screenState.loading
    ) {
        withContext(Dispatchers.Main) {
            screenState = screenState.copyWith(currenciesState, baseCurrencyCode, loading)
        }
    }

    private fun findBaseRateIndexByCode(code: String): Int {
        if (screenState.currenciesState is CurrenciesState.Success) {
            (screenState.currenciesState as CurrenciesState.Success).currencies.forEachIndexed { index, rate ->
                if (rate.code == code) return index
            }
        }
        return -1
    }

    data class ScreenState(
        val currenciesState: CurrenciesState,
        val baseCurrencyCode: String,
        val loading: Boolean
    ) {

        fun copyWith(
            currenciesState: CurrenciesState = this.currenciesState,
            baseCurrencyCode: String = this.baseCurrencyCode,
            loading: Boolean = this.loading
        ): ScreenState {

            return ScreenState(
                currenciesState,
                baseCurrencyCode,
                loading
            )
        }
    }
}