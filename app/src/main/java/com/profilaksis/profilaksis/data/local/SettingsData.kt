package com.profilaksis.profilaksis.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.res.painterResource
import com.profilaksis.profilaksis.R
import com.profilaksis.profilaksis.data.model.SettingsItem

object SettingsData {
    val settings = listOf(
        SettingsItem(
            title = "Account Settings",
            icon = R.drawable.profile_idle,
            tag = "account"
        ),
        SettingsItem(
            title = "Display",
            icon = R.drawable.mode,
            tag = "display"
        ),
        SettingsItem(
            title = "Help & Support",
            icon = R.drawable.information,
            tag = "help"
        ),
        SettingsItem(
            title = "Terms & Conditions",
            icon = R.drawable.terms,
            tag = "terms"
        ),
        SettingsItem(
            title = "Privacy Policy",
            icon = R.drawable.secure,
            tag = "privacy"
        ),
        SettingsItem(
            title = "Logout",
            icon = R.drawable.logout,
            tag = "logout"
        ),
    )
}