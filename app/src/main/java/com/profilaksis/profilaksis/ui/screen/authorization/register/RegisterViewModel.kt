package com.profilaksis.profilaksis.ui.screen.authorization.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.RegisterResponse
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.data.remote.requestdata.RegisterRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: Repository) : ViewModel() {


    private val _uiState = MutableStateFlow<RegisterUiState>(
        RegisterUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun register(dataRegister: RegisterRequestBody) {
        viewModelScope.launch {
            try {
                val message = repository.registerUser(dataRegister)
                _uiState.value = RegisterUiState.Success(message)
            } catch (e: Exception) {
                _uiState.value = RegisterUiState.Error("Failed: ${e.message}")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = RegisterUiState.Initial
    }
    fun loadingUiState() {
        _uiState.value = RegisterUiState.Loading
    }
}

sealed class RegisterUiState {
    object Loading : RegisterUiState()
    object Initial : RegisterUiState()
    data class Success(val result: RegisterResponse) : RegisterUiState()
    data class Error(val errorMessage: String) : RegisterUiState()
}