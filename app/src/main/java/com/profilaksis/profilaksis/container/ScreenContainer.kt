package com.profilaksis.profilaksis.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.profilaksis.profilaksis.ui.screen.adds.AddsScreen
import com.profilaksis.profilaksis.ui.screen.diabetes.DiabetesScreen
import com.profilaksis.profilaksis.ui.screen.heart.HeartScreen

@Composable
fun ScreenContainer(id: String, clickBack: () -> Unit) {

    var isPremium by remember { mutableStateOf(false) }

    if (!isPremium) {
        AddsScreen(onClickExit = {
            isPremium = true
        })
    }

    if (isPremium) {
        if (id == "heart") {
            HeartScreen(clickBack = clickBack)
        } else if (id == "diabetes") {
            DiabetesScreen(clickBack = clickBack)
        }
    }
}