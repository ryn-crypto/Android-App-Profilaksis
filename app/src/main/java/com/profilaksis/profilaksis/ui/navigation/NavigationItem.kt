package com.profilaksis.profilaksis.ui.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavigationItem(
    val title: String,
    val iconIdle: Painter,
    val iconActive: Painter,
    val contentDescription: String,
    val screen: Screen
)