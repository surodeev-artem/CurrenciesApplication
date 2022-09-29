package com.surodeev.currenciestestapplication.ui.widget.app_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InitialAppBar() {
    Text(
        text = "Загрузка...",
        color = Color.Black.copy(alpha = 0.8f),
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
    )
}