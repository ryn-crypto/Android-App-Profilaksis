package com.profilaksis.profilaksis.utils

import android.content.SharedPreferences
import com.profilaksis.profilaksis.data.model.UserLogin

class AuthPreferences(private val preferences: SharedPreferences) {

    fun saveLoginInfo(dataUser: UserLogin) {
        preferences.edit().apply {
            putInt("id", dataUser.id)
            putString("username", dataUser.username)
            putString("avatar", dataUser.avatar)
            putString("token", dataUser.token)
            putString("email", dataUser.email)
            putString("role", dataUser.role)
            apply()
        }
    }

    fun getLoggedInfo(): UserLogin? {
        val id = preferences.getInt("id", 0)
        val username = preferences.getString("username", null)
        val avatar = preferences.getString("avatar", null)
        val token = preferences.getString("token", null)
        val email = preferences.getString("email", null)
        val role = preferences.getString("role", null)

        return if (token != null && username != null && id != 0 && avatar != null && email != null && role != null) {
            UserLogin(id, username, email, role, avatar, token)
        } else {
            null
        }
    }

    fun clearLoginInfo() {
        preferences.edit()
            .remove("id")
            .remove("username")
            .remove("avatar")
            .remove("token")
            .remove("email")
            .remove("role")
            .apply()
    }
}
