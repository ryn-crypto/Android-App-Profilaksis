package com.profilaksis.profilaksis.ui.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.profilaksis.profilaksis.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import perfetto.protos.UiState

class ProfileViewModel(private val repository: Repository) : ViewModel() {

    private val _percentage = MutableLiveData<Int>()
    val percentage =
        if (_percentage.value == 0) {
            getPercentage()
        } else {
            _percentage.value
        }

//    fun getProfile() = repository.getProfile()

    fun getPercentage() {
        _percentage.value = repository.getPercentage()
    }
}