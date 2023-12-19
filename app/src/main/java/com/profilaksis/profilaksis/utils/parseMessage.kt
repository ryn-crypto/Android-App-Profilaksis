package com.profilaksis.profilaksis.utils

import org.json.JSONObject

fun parseErrorMessage(errorBody: String): String {
    val json = JSONObject(errorBody)
    return json.getString("message")
}
