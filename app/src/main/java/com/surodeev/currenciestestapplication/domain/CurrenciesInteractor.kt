package com.surodeev.currenciestestapplication.domain

import android.util.Log
import com.surodeev.currenciestestapplication.entity.CurrenciesState
import com.surodeev.currenciestestapplication.entity.Currency
import com.surodeev.currenciestestapplication.entity.FavoriteCurrency
import com.surodeev.currenciestestapplication.repository.CurrenciesRepository
import com.surodeev.currenciestestapplication.ui.viewmodel.FilterViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesInteractor @Inject constructor(private val repository: CurrenciesRepository) {

    fun getCurrencies(baseCurrency: String, sortVariant: FilterViewModel.SortVariant): CurrenciesState {
        return try {
            val response = repository.getLatestRates("55jkKaGlIBlujIiY2vPmTGatN21b3Q1w", baseCurrency)
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()
                if (body != null) {
                    val favoritesCurrencies = getFavoritesCurrencies()
                    val favoritesCurrenciesList = mutableListOf<Currency>()
                    val mainCurrenciesList = body.rates.toCurrenciesList(sortVariant).map { currency ->
                        if (favoritesCurrencies.hasCodeMatch(currency.code)) {
                            currency.favorite = true
                            favoritesCurrenciesList.add(currency)
                        }
                        return@map currency
                    }
                    CurrenciesState.Success(
                        body.timestamp,
                        mainCurrenciesList,
                        favoritesCurrenciesList
                    )
                } else {
                    CurrenciesState.Failed(response.errorBody()?.string() ?: "Unknown error")
                }
            } else {
                CurrenciesState.Failed(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            CurrenciesState.Failed(e.message ?: "Unknown error")
        }
    }

    fun sortRates(unsortedRates: List<Currency>, sortVariant: FilterViewModel.SortVariant): List<Currency> {
        return when (sortVariant) {
            FilterViewModel.SortVariant.ALPHABET_ASC -> unsortedRates.sortByCodeAsc()
            FilterViewModel.SortVariant.ALPHABET_DESC -> unsortedRates.sortByCodeDesc()
            FilterViewModel.SortVariant.VALUE_ASC -> unsortedRates.sortByValueAsc()
            FilterViewModel.SortVariant.VALUE_DESC -> unsortedRates.sortByValueDesc()
        }
    }

    fun getFavoritesCurrencies(): List<FavoriteCurrency> {
        return repository.getFavoritesCurrencies()
    }

    fun insertFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        repository.insertFavoriteCurrency(favoriteCurrency)
    }

    fun deleteFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        repository.deleteFavoriteCurrency(favoriteCurrency)
    }

    private fun Map<String, Double>.toCurrenciesList(sortVariant: FilterViewModel.SortVariant): List<Currency> {
        val ratesList = mutableListOf<Currency>()
        entries.forEach { (code, value) ->
            ratesList.add(Currency(code, value))
        }
        return sortRates(ratesList, sortVariant)
    }

    private fun List<Currency>.sortByCodeAsc(): List<Currency> {
        return this.sortedWith { first, second ->
            if (first.code > second.code) 1
            else if (first.code == second.code) 0
            else -1
        }
    }

    private fun List<Currency>.sortByCodeDesc(): List<Currency> {
        return this.sortedWith { first, second ->
            if (first.code < second.code) 1
            else if (first.code == second.code) 0
            else -1
        }
    }

    private fun List<Currency>.sortByValueAsc(): List<Currency> {
        return this.sortedWith { first, second ->
            if (first.value > second.value) 1
            else if (first.value == second.value) 0
            else -1
        }
    }

    private fun List<Currency>.sortByValueDesc(): List<Currency> {
        return this.sortedWith { first, second ->
            if (first.value < second.value) 1
            else if (first.value == second.value) 0
            else -1
        }
    }

    private fun List<FavoriteCurrency>.hasCodeMatch(code: String): Boolean {
        forEach { currency ->
            if (currency.code == code) return true
        }
        return false
    }
}