package com.profilaksis.profilaksis.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import com.profilaksis.profilaksis.data.model.SettingsItem

object SettingsData {
    val settings = listOf(
        SettingsItem(
            title = "Account Settings",
            icon = Icons.Filled.Person
        ),
        SettingsItem(
            title = "Display",
            icon = Icons.Filled.Info
        ),
        SettingsItem(
            title = "Help & Support",
            icon = Icons.Filled.Info
        ),
        SettingsItem(
            title = "Terms & Conditions",
            icon = Icons.Filled.Info
        ),
        SettingsItem(
            title = "Privacy Policy",
            icon = Icons.Filled.Info
        ),
        SettingsItem(
            title = "About",
            icon = Icons.Filled.Info
        ),
    )
}