package com.profilaksis.profilaksis.ui.screen.diabetes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.ResultData
import com.profilaksis.profilaksis.data.remote.requestdata.PredictRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiabetesViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<DiabetesUiState>(
        DiabetesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun sendData(dataRequest: PredictRequestBody, token: String) {
        Log.e("test123", dataRequest.toString())
        viewModelScope.launch {
            try {
                val responseData = repository.sendData(dataRequest, token, "diabetes")
                Log.e("test123", responseData.toString())
                _uiState.value = DiabetesUiState.Success(responseData.data!!)
            } catch (e: Exception) {
                _uiState.value = DiabetesUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
    fun resetUiState() {
        _uiState.value = DiabetesUiState.Initial(ResultData())
    }
}

sealed class DiabetesUiState {
    object Loading : DiabetesUiState()
    data class Initial(val result: ResultData) : DiabetesUiState()
    data class Success(val result: ResultData) : DiabetesUiState()
    data class Error(val errorMessage: String) : DiabetesUiState()
}