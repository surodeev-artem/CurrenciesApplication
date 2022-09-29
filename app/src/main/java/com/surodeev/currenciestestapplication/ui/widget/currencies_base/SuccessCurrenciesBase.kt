package com.surodeev.currenciestestapplication.ui.widget.currencies_base

import androidx.compose.runtime.Composable
import com.surodeev.currenciestestapplication.entity.CurrenciesState
import com.surodeev.currenciestestapplication.entity.Currency
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat

@Composable
fun SuccessCurrenciesBase(currenciesState: CurrenciesState.Success, favorites: Boolean = false, clickFavoritesBtn: (Currency) -> Unit) {
    val dateTime = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm:ss a")
        .withZone(DateTimeZone.getDefault())
        .print(currenciesState.timestamp * 1000)
    val currencies = if (favorites) currenciesState.favoritesCurrencies else currenciesState.currencies
    CurrenciesList("Курс валют на $dateTime", currencies) {
        clickFavoritesBtn(it)
    }
}