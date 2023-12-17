package com.profilaksis.profilaksis.ui.screen.heart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.HistoryData
import com.profilaksis.profilaksis.data.model.ResponseResult
import com.profilaksis.profilaksis.data.model.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeartViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<HeartUiState>(
        HeartUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun sendData() {
        viewModelScope.launch {
            try {
                val sendData = repository.sendData()
                _uiState.value = HeartUiState.Success(sendData)
            } catch (e: Exception) {
                _uiState.value = HeartUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
    fun resetUiState() {
        _uiState.value = HeartUiState.Initial(ResponseResult())
    }
}

sealed class HeartUiState {
    object Loading : HeartUiState()
    data class Initial(val result: ResponseResult) : HeartUiState()
    data class Success(val result: ResponseResult) : HeartUiState()
    data class Error(val errorMessage: String) : HeartUiState()
}