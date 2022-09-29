package com.surodeev.currenciestestapplication.ui.widget.app_bar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FailedAppBar() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Ошибка",
            color = Color.Black.copy(alpha = 0.8f),
            modifier = Modifier.padding(start = 16.dp).weight(1f)
        )
    }
}