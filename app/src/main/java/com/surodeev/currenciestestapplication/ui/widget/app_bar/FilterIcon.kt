package com.surodeev.currenciestestapplication.ui.widget.app_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.surodeev.currenciestestapplication.ui.theme.Secondary

@Composable
fun FilterIcon(onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Filled.FilterList,
        contentDescription = "Filter",
        modifier = Modifier
            .padding(end = 8.dp)
            .size(24.dp)
            .clickable {
                onClick()
            },
        tint = Secondary
    )
}