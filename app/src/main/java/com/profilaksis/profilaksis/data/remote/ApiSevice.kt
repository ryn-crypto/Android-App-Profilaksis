package com.profilaksis.profilaksis.data.remote

import com.profilaksis.profilaksis.data.model.LoginResponse
import com.profilaksis.profilaksis.data.model.RegisterResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
//    @GET("/users/{id}/{type}")
//    fun getUsers(
//        @Path("id") id: String,
//        @Path("type") type: String
//    ): Call<List<>>

    @POST("/auth/register")
    fun registerUser(
        @Body userRequestBody: RequestBody
    ): Call<RegisterResponse>

    @POST("/auth/login")
    fun loginUser(
        @Body userRequestBody: RequestBody
    ): Call<LoginResponse>
}