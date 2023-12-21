package com.profilaksis.profilaksis.ui.screen.adds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddsScreen(onClickExit: () -> Unit) {
    var countdown by remember { mutableIntStateOf(10) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        scope.launch {
            for (i in countdown downTo 1) {
                delay(1000)
                countdown = i
            }
            onClickExit()
        }
    }

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
            if (countdown > 0) {
                Text(text = countdown.toString())
            } else {
                Button(
                    onClick = onClickExit,
                ) {
                    Text(text = "x")
                }
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