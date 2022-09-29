package com.surodeev.currenciestestapplication.ui.widget.currencies_base

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.surodeev.currenciestestapplication.entity.Currency

@Composable
fun CurrenciesList(header: String, currencies: List<Currency>, clickFavoritesBtn: (Currency) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                header,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Thin
            )
        }
        items(currencies.size) { index ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Box(Modifier.width(64.dp)) {
                    Text(currencies[index].code)
                }
                Spacer(Modifier.width(48.dp))
                Text(currencies[index].value.toString(), textAlign = TextAlign.Start)
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { clickFavoritesBtn(currencies[index]) }) {
                    if (currencies[index].favorite) {
                        Icon(
                            Icons.Filled.Favorite,
                            "Favorite",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Red.copy(alpha = 0.7f)
                        )
                    } else {
                        Icon(
                            Icons.Filled.FavoriteBorder,
                            "Favorite",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black.copy(alpha = 0.2f)
                        )
                    }
                }
            }
            if (index < currencies.lastIndex) {
                Divider(
                    color = Color.Black.copy(alpha = 0.1f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(end = 64.dp)
                )
            }
        }
    }
}