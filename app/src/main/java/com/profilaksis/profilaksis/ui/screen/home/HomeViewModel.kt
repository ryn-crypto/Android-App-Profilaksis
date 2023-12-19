package com.profilaksis.profilaksis.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.ResponseArticleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(
        HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getDataArticle() {
        viewModelScope.launch {
            try {
                val dataArticle = repository.getAllArticle()
                _uiState.value = HomeUiState.Success(dataArticle.data as List<ResponseArticleItem>)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val result: List<ResponseArticleItem> ) : HomeUiState()
    data class Error(val errorMessage: String) : HomeUiState()
}