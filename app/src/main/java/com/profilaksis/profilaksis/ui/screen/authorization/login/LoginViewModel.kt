package com.profilaksis.profilaksis.ui.screen.authorization.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.ResponseResult
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.ui.screen.heart.HeartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {


    private val _uiState = MutableStateFlow<LoginUiState>(
        LoginUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val dataUser = repository.login(email, password)
                _uiState.value = LoginUiState.Success(dataUser)
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error("Failed: ${e.message}")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Initial
    }
}

sealed class LoginUiState {
    object Loading : LoginUiState()
    object Initial : LoginUiState()
    data class Success(val result: UserLogin) : LoginUiState()
    data class Error(val errorMessage: String) : LoginUiState()
}