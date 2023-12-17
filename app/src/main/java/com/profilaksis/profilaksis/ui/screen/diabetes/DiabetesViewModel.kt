package com.profilaksis.profilaksis.ui.screen.diabetes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.ResponseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiabetesViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<DiabetesUiState>(
        DiabetesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun sendData() {
        viewModelScope.launch {
            try {
                val sendData = repository.sendData()
                _uiState.value = DiabetesUiState.Success(sendData)
            } catch (e: Exception) {
                _uiState.value = DiabetesUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
    fun resetUiState() {
        _uiState.value = DiabetesUiState.Initial(ResponseResult())
    }
}

sealed class DiabetesUiState {
    object Loading : DiabetesUiState()
    data class Initial(val result: ResponseResult) : DiabetesUiState()
    data class Success(val result: ResponseResult) : DiabetesUiState()
    data class Error(val errorMessage: String) : DiabetesUiState()
}