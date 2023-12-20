package com.profilaksis.profilaksis.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.ResultsItem
import com.profilaksis.profilaksis.data.remote.requestdata.PredictRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<HistoryUiState>(
        HistoryUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    fun getDataHistory(token: String) {
        viewModelScope.launch {
            try {
                val dataHistory = repository.getHistory(token)
                _uiState.value = HistoryUiState.Success(dataHistory.results as List<ResultsItem>)
            } catch (e: Exception) {
                _uiState.value = HistoryUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
}

sealed class HistoryUiState {
    object Loading : HistoryUiState()
    data class Success(val result: List<ResultsItem>) : HistoryUiState()
    data class Error(val errorMessage: String) : HistoryUiState()
}