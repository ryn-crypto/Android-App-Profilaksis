package com.profilaksis.profilaksis.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date): String {
    val localeIndonesia = Locale("id", "ID")
    val format = SimpleDateFormat("dd MMMM yyyy", localeIndonesia)
    return format.format(date)
}