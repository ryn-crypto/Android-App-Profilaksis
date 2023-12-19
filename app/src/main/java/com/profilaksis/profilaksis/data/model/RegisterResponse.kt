package com.profilaksis.profilaksis.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("message")
    val message: String? = null,
)
