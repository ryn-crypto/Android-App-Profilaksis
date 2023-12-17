package com.profilaksis.profilaksis.data.model

data class RadioButtonInfo<T>(
    val status: T,
    val name: String,
    val selectedStatus: T,
    val onStatusSelected: (T) -> Unit
)

