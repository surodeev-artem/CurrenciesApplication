package com.surodeev.currenciestestapplication.repository

import com.surodeev.currenciestestapplication.entity.CurrenciesResponse
import com.surodeev.currenciestestapplication.entity.FavoriteCurrency
import com.surodeev.currenciestestapplication.repository.api.CurrenciesDatabase
import com.surodeev.currenciestestapplication.repository.api.ExchangeratesApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesRepository @Inject constructor(
    private val api: ExchangeratesApi,
    private val db: CurrenciesDatabase
) {

    fun getLatestRates(apiKey: String, baseCurrency: String): Response<CurrenciesResponse> {
        return api.getLatestRates(apiKey, baseCurrency).execute()
    }

    fun getFavoritesCurrencies(): List<FavoriteCurrency> {
        return db.favoritesDao().getAll()
    }

    fun insertFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        db.favoritesDao().insertCurrency(favoriteCurrency)
    }

    fun deleteFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        db.favoritesDao().deleteCurrency(favoriteCurrency)
    }
}