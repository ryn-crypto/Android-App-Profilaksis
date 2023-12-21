package com.profilaksis.profilaksis.utils

import androidx.compose.ui.res.stringResource
import com.profilaksis.profilaksis.R
import java.util.*

object Greeting {
    fun getGreeting(): Int {
        val calendar = Calendar.getInstance()

        return when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> R.string.good_morning
            in 12..15 -> R.string.good_afternoon
            in 16..20 -> R.string.good_evening
            else -> R.string.good_night
        }
    }
}
