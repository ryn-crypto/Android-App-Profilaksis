package com.profilaksis.profilaksis.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val contentDescription: String,
    val screen: Screen
)