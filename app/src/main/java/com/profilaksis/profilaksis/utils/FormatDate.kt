package com.profilaksis.profilaksis.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date): String {
    val format = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
    return format.format(date)
}