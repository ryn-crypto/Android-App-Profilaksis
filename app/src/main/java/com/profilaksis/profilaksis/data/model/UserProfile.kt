package com.profilaksis.profilaksis.data.model

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: UserData
)

data class UserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("token")
    val token: String
)
