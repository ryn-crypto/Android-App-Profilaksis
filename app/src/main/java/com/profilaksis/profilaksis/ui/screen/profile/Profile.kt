package com.profilaksis.profilaksis.ui.screen.profile

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen() {
    Row {
        Column {
            Text(text = "Profile")
            Log.e("test123", "ProfileScreen tampil")
        }
    }
}