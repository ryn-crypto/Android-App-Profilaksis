package com.profilaksis.profilaksis.ui.screen.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profilaksis.profilaksis.data.Repository
import com.profilaksis.profilaksis.data.model.ResponseArticleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ResultViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<ResultUiState>(
        ResultUiState.Loading)
    val uiState =
        if (_uiState.value is ResultUiState.Loading) {
            getArticle()
            _uiState.asStateFlow()
        } else {
            _uiState.asStateFlow()
        }

    private fun getArticle() {
        viewModelScope.launch {
            try {
                val getArticle = repository.getArticle()
                _uiState.value = ResultUiState.Success(getArticle)
            } catch (e: Exception) {
                _uiState.value = ResultUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
    fun resetUiState() {
        _uiState.value = ResultUiState.Initial(ResponseArticleItem())
    }
}

sealed class ResultUiState {
    object Loading : ResultUiState()
    data class Initial(val result: ResponseArticleItem) : ResultUiState()
    data class Success(val result: List<ResponseArticleItem>) : ResultUiState()
    data class Error(val errorMessage: String) : ResultUiState()
}