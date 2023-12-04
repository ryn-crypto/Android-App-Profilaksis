package com.profilaksis.profilaksis.utils

import androidx.appcompat.app.AppCompatDelegate

enum class NightMode(val value: Int) {

    AUTO(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),

    ON(AppCompatDelegate.MODE_NIGHT_YES),

    OFF(AppCompatDelegate.MODE_NIGHT_NO)
}