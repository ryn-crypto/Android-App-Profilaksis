package com.profilaksis.profilaksis.ui.screen.history

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HistoryScreen() {
    Row {
        Column {
            Text(text = "History")
            Log.e("test123", "HistoryScreen tampil")
        }
    }
}