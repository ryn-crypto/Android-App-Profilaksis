package com.profilaksis.profilaksis.ui.screen.home

import androidx.lifecycle.ViewModel
import com.profilaksis.profilaksis.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import perfetto.protos.UiState

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {
//    private val _uiState: MutableStateFlow<UiState<List<OrderItemProduct>>> = MutableStateFlow(UiState.Loading)
//    val uiState: StateFlow<UiState<List<OrderItemProduct>>>
//        get() = _uiState
//
//    fun getAllProduct() {
//        viewModelScope.launch {
//            repository.getAllProducts()
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { orderItem ->
//                    _uiState.value = UiState.Success(orderItem)
//                }
//        }
//    }
//    fun getProductByName(name: String) {
//        viewModelScope.launch {
//            repository.getProductByName(name)
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { orderItem ->
//                    _uiState.value = UiState.Success(orderItem)
//                }
//        }
//    }
}