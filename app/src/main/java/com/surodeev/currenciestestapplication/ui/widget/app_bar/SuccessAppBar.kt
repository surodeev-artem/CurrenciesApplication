package com.surodeev.currenciestestapplication.ui.widget.app_bar

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.surodeev.currenciestestapplication.entity.Currency
import com.surodeev.currenciestestapplication.ui.theme.Secondary

@Composable
fun SuccessAppBar(
    currencies: List<Currency>,
    baseCurrencyCode: String,
    rowState: LazyListState,
    baseCurrencyClick: (String) -> Unit,
    filterClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        LazyRow(
            state = rowState,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(count = currencies.size, itemContent = { index ->
                Text(
                    currencies[index].code,
                    modifier = Modifier
                        .clickable {
                            baseCurrencyClick(currencies[index].code)
                        }
                        .padding(8.dp),
                    fontWeight = FontWeight.SemiBold,
                    color = if (baseCurrencyCode == currencies[index].code) {
                        Secondary
                    } else {
                        Color.Black.copy(
                            alpha = 0.7f
                        )
                    }
                )
            })
        }
        FilterIcon(filterClick)
    }
}