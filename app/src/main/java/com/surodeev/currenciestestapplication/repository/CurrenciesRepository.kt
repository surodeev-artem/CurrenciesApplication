package com.surodeev.currenciestestapplication.repository

import com.surodeev.currenciestestapplication.entity.CurrenciesResponse
import com.surodeev.currenciestestapplication.repository.api.ExchangeratesApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesRepository @Inject constructor(private val api: ExchangeratesApi) {

    fun getLatestRates(apiKey: String, baseCurrency: String): Response<CurrenciesResponse> {
        return api.getLatestRates(apiKey, baseCurrency).execute()
    }
}