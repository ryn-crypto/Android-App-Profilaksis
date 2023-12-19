package com.profilaksis.profilaksis.ui.screen.authorization.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.LoginResponse
import com.profilaksis.profilaksis.data.remote.requestdata.LoginRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {


    private val _uiState = MutableStateFlow<LoginUiState>(
        LoginUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun login(dataLogin: LoginRequestBody) {
        viewModelScope.launch {
            try {
                val dataUser = repository.loginUser(dataLogin)
                _uiState.value = LoginUiState.Success(dataUser)
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error("Failed: ${e.message}")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Initial
    }

    fun loadingUiState() {
        _uiState.value = LoginUiState.Loading
    }
}

sealed class LoginUiState {
    object Loading : LoginUiState()
    object Initial : LoginUiState()
    data class Success(val result: LoginResponse) : LoginUiState()
    data class Error(val errorMessage: String) : LoginUiState()
}