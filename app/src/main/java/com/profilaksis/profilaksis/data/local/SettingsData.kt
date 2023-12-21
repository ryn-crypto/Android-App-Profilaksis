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
            title = R.string.account,
            icon = R.drawable.profile_idle,
            tag = "account"
        ),
        SettingsItem(
            title = R.string.mode,
            icon = R.drawable.mode,
            tag = "display"
        ),
        SettingsItem(
            title = R.string.support,
            icon = R.drawable.information,
            tag = "help"
        ),
        SettingsItem(
            title = R.string.terms,
            icon = R.drawable.terms,
            tag = "terms"
        ),
        SettingsItem(
            title = R.string.privacy,
            icon = R.drawable.secure,
            tag = "privacy"
        ),
        SettingsItem(
            title = R.string.logout,
            icon = R.drawable.logout,
            tag = "logout"
        ),
    )
}