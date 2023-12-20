package com.profilaksis.profilaksis.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import com.profilaksis.profilaksis.data.model.SettingsItem

object SettingsData {
    val settings = listOf(
        SettingsItem(
            title = "Account Settings",
            icon = Icons.Filled.Person,
            tag = "account"
        ),
        SettingsItem(
            title = "Display",
            icon = Icons.Filled.Info,
            tag = "display"
        ),
        SettingsItem(
            title = "Help & Support",
            icon = Icons.Filled.Info,
            tag = "help"
        ),
        SettingsItem(
            title = "Terms & Conditions",
            icon = Icons.Filled.Info,
            tag = "terms"
        ),
        SettingsItem(
            title = "Privacy Policy",
            icon = Icons.Filled.Info,
            tag = "privacy"
        ),
        SettingsItem(
            title = "Logout",
            icon = Icons.Filled.ExitToApp,
            tag = "logout"
        ),
    )
}