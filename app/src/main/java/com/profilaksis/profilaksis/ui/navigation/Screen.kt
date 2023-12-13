package com.profilaksis.profilaksis.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object History : Screen("history")
    object Heart : Screen("heart")
    object Diabetes : Screen("diabetes")
    object Result : Screen("result/{resultId}") {
        fun createRoute(resultId: Long) = "home/$resultId"
    }
}
