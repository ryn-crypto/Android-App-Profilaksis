package com.profilaksis.profilaksis.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ResponseResult(

    @field:SerializedName("prediction")
    val prediction: Float? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("date")
    val date: Date? = null,

    @field:SerializedName("userName")
    val userName: String? = null,

    @field:SerializedName("description")
    val description: String? = null,
)
