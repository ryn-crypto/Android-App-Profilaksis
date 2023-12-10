package com.profilaksis.profilaksis.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize(1f )
            .background(Color.DarkGray)
    ) {
        Column {
            Text(text = "Home")
            Log.e("test123", "HomeScreen tampil")
        }
    }
}