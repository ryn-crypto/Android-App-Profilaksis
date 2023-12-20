package com.profilaksis.profilaksis.ui.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.HistoryResponse
import com.profilaksis.profilaksis.data.model.ResultsItem
import com.profilaksis.profilaksis.data.model.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ProfileViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadData(token: String) {
        viewModelScope.launch {
            try {
                val lastHistory = repository.getHistory(token)
                val profile = repository.getProfile()
                val history = if (lastHistory.results?.size!! > 0) {
                    lastHistory.results.last()
                } else {
                    ResultsItem(null, null, null, null, null, null, null)
                }
                _uiState.value = ProfileUiState.Success(history, profile)
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
}

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val lastStory: ResultsItem?, val profile: UserData?) : ProfileUiState()
    data class Error(val errorMessage: String) : ProfileUiState()
}
