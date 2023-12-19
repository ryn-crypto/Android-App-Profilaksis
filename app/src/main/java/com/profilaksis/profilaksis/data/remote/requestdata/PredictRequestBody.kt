package com.profilaksis.profilaksis.data.remote.requestdata

data class PredictRequestBody(
    val kelamin: Int,
    val umur: Int,
    val bmi: Int,
    val tekananDarah: Int,
    val kolesterol: Int,
    val stroke: Int,
    val diabetes: Int?,
    val sakitJantung: Int?,
    val rokok: Int,
    val alkohol: Int,
    val olahraga: Int,
    val buah: Int,
    val sayur: Int,
    val susahJalan: Int,
)

