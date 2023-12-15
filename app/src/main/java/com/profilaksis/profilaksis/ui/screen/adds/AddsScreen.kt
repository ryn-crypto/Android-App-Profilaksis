package com.profilaksis.profilaksis.ui.screen.adds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddsScreen(onClickExit: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Button(
                onClick = onClickExit,
            ) {
                Text(text = "x")
            }
        }
        Text(
            text = "Adds Screen"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AddsPreview() {
    AddsScreen(
        onClickExit = {}
    )
}