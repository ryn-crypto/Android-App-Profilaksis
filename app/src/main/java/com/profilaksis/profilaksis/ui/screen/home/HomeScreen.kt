package com.profilaksis.profilaksis.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Row {
        Column {
            Text(text = "Home")
            Log.e("test123", "HomeScreen tampil")
        }
    }
}