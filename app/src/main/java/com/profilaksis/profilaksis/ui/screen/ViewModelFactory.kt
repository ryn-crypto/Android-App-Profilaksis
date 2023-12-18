package com.profilaksis.profilaksis.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.ui.screen.authorization.AuthViewModel
import com.profilaksis.profilaksis.ui.screen.authorization.login.LoginViewModel
import com.profilaksis.profilaksis.ui.screen.diabetes.DiabetesViewModel
import com.profilaksis.profilaksis.ui.screen.heart.HeartViewModel
import com.profilaksis.profilaksis.ui.screen.history.HistoryViewModel
import com.profilaksis.profilaksis.ui.screen.home.HomeViewModel
import com.profilaksis.profilaksis.ui.screen.profile.ProfileViewModel
import com.profilaksis.profilaksis.ui.screen.result.ResultViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HeartViewModel::class.java)) {
            return HeartViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DiabetesViewModel::class.java)) {
            return DiabetesViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}