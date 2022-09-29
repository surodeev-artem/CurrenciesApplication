package com.surodeev.currenciestestapplication.ui.widget.filter

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.surodeev.currenciestestapplication.ui.theme.Primary
import com.surodeev.currenciestestapplication.ui.theme.Secondary

@Composable
fun CheckboxItem(checked: Boolean, title: String, onCheckChange: (Boolean) -> Unit) {
    Row {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChange,
            colors = CheckboxDefaults.colors(checkedColor = Primary.copy(0.6f), checkmarkColor = Secondary)
        )
        Spacer(Modifier.width(8.dp))
        Text(title, modifier = Modifier.align(Alignment.CenterVertically), fontSize = 16.sp)
    }
}