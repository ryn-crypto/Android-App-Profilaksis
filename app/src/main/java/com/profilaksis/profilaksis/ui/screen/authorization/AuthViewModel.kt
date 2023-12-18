package com.profilaksis.profilaksis.ui.screen.authorization

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.profilaksis.profilaksis.MyApplication
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.UserLogin

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val loginManager = (application as MyApplication).authManager

    fun saveLoginInfo(userData: UserLogin) {
        loginManager.saveLoginInfo(userData)
    }

    fun getLoggedInfo(): UserLogin? {
        return loginManager.getLoggedInfo()
    }

    fun clearLoginInfo() {
        loginManager.clearLoginInfo()
    }
}