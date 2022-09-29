package com.surodeev.currenciestestapplication.repository.api

import com.surodeev.currenciestestapplication.entity.CurrenciesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ExchangeratesApi {
    @GET("latest")
    fun getLatestRates(@Header("apikey") apiKey: String, @Query("base") baseCurrency: String): Call<CurrenciesResponse>
}