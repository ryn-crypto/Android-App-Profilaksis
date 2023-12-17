package com.profilaksis.profilaksis.utils

import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

@Composable
fun RedText(text: String) {
    BasicText(
        text = text,
        style = TextStyle(
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
            fontSize = 10.sp
        )
    )
}
