package com.profilaksis.profilaksis.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: UserLogin? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class UserLogin(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("avatar")
	val avatar: String,

	@field:SerializedName("token")
	val token: String
)