package com.profilaksis.profilaksis.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.HistoryData
import com.profilaksis.profilaksis.data.model.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ProfileViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            try {
                val lastStory = repository.getLastHistory()
                val profile = repository.getProfile()
                _uiState.value = ProfileUiState.Success(lastStory, profile)
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
}

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val lastStory: HistoryData?, val profile: UserData?) : ProfileUiState()
    data class Error(val errorMessage: String) : ProfileUiState()
}
