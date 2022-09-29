package com.surodeev.currenciestestapplication.ui.widget.currencies_base

import androidx.compose.runtime.Composable
import com.surodeev.currenciestestapplication.entity.CurrenciesState
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat

@Composable
fun SuccessCurrenciesBase(currenciesState: CurrenciesState.Success) {
    val dateTime = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm:ss a")
        .withZone(DateTimeZone.getDefault())
        .print(currenciesState.timestamp * 1000)

    CurrenciesList("Курс валют на $dateTime", currenciesState.currencies) {

    }
}