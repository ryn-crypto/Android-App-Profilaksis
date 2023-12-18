package com.profilaksis.profilaksis

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.profilaksis.profilaksis.utils.AuthPreferences
import com.profilaksis.profilaksis.utils.NightMode
import java.util.*

class MyApplication : Application() {

    lateinit var authManager: AuthPreferences
        private set

    override fun onCreate() {
        super.onCreate()

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        preferences.getString(
            getString(R.string.pref_key_dark),
            getString(R.string.pref_dark_auto)
        )?.apply {
            val mode = NightMode.valueOf(this.uppercase(Locale.US))
            AppCompatDelegate.setDefaultNightMode(mode.value)
        }

        initializeLoginManager(preferences)
    }

    private fun initializeLoginManager(preferences: SharedPreferences) {
        authManager = AuthPreferences(preferences)
    }
}