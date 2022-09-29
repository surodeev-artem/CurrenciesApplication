package com.surodeev.currenciestestapplication.di

import com.surodeev.currenciestestapplication.repository.api.ExchangeratesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideExchangeratesApi(): ExchangeratesApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.apilayer.com/exchangerates_data/")
        .build()
        .create(ExchangeratesApi::class.java)
}