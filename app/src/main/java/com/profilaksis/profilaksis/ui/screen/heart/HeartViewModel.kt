package com.profilaksis.profilaksis.ui.screen.heart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.ResultData
import com.profilaksis.profilaksis.data.remote.requestdata.PredictRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeartViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<HeartUiState>(
        HeartUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun sendData(dataRequest: PredictRequestBody, token: String) {
        viewModelScope.launch {
            try {
                val data = repository.sendData(dataRequest, token, "heart")
                _uiState.value = HeartUiState.Success(data.data!!)
                Log.e("test data dipanggil", data.data.toString())
            } catch (e: Exception) {
                _uiState.value = HeartUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
    fun resetUiState() {
        _uiState.value = HeartUiState.Initial(ResultData())
    }
}

sealed class HeartUiState {
    object Loading : HeartUiState()
    data class Initial(val result: ResultData) : HeartUiState()
    data class Success(val result: ResultData) : HeartUiState()
    data class Error(val errorMessage: String) : HeartUiState()
}