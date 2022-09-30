package com.surodeev.currenciestestapplication.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surodeev.currenciestestapplication.domain.CurrenciesInteractor
import com.surodeev.currenciestestapplication.entity.CurrenciesState
import com.surodeev.currenciestestapplication.entity.Currency
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
                screenState = screenState.copy(
                    currenciesState = (screenState.currenciesState as CurrenciesState.Success).copy(
                        currencies = interactor.sortRates(
                            (screenState.currenciesState as CurrenciesState.Success).currencies, value
                        ),
                        favoritesCurrencies = interactor.sortRates(
                            (screenState.currenciesState as CurrenciesState.Success).favoritesCurrencies, value
                        ),
                    )
                )
            }
        }

    init {
        fetchData()
    }

    fun fetchData() {
        val baseCurrency = screenState.baseCurrencyCode
        screenState = screenState.copy(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val currenciesState = interactor.getCurrencies(
                baseCurrency, sortVariant
            )
            withContext(Dispatchers.Main) {
                screenState = screenState.copy(currenciesState, loading = false)
            }
        }
    }

    fun changeBaseCurrency(currencyCode: String) {
        screenState = screenState.copy(baseCurrencyCode = currencyCode)
        fetchData()
    }

    fun clickFavoriteBtn(currency: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            if (screenState.currenciesState is CurrenciesState.Success) {
                val newScreenState = interactor.clickFavoriteBtn(currency, screenState)
                withContext(Dispatchers.Main) {
                    screenState = newScreenState
                }

            }
        }
    }

    data class ScreenState(
        val currenciesState: CurrenciesState,
        val baseCurrencyCode: String,
        val loading: Boolean
    )
}