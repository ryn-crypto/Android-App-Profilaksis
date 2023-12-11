package com.profilaksis.profilaksis.utils

import java.util.*

object Greeting {
    fun getGreeting(): String {
        val calendar = Calendar.getInstance()

        return when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good morning"
            in 12..15 -> "Good afternoon"
            in 16..20 -> "Good evening"
            else -> "Good night"
        }
    }
}
