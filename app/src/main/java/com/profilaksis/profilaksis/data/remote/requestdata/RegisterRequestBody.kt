package com.profilaksis.profilaksis.data.remote.requestdata

data class RegisterRequestBody (
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword : String
)