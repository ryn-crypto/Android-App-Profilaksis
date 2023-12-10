package com.profilaksis.profilaksis.ui.screen.history

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Text(
            text = "History",
            modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
        )
        Log.e("test123", "HistoryScreen tampil")
    }
}