package com.profilaksis.profilaksis.data.remote

import com.profilaksis.profilaksis.data.model.HistoryResponse
import com.profilaksis.profilaksis.data.model.LoginResponse
import com.profilaksis.profilaksis.data.model.PredictionResponse
import com.profilaksis.profilaksis.data.model.RegisterResponse
import com.profilaksis.profilaksis.data.model.ResponseArticle
import com.profilaksis.profilaksis.data.model.ResponseArticleItem
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
        @Body userRequestBody: RequestBody,
    ): Call<RegisterResponse>

    @POST("/auth/login")
    fun loginUser(
        @Body userRequestBody: RequestBody,
    ): Call<LoginResponse>

    @POST("/prediksi/diabetes")
    fun predictDiabetes(
        @Body userRequestBody: RequestBody,
    ): Call<PredictionResponse>


    @POST("/prediksi/jantung")
    fun predictHeart(
        @Body userRequestBody: RequestBody,
    ): Call<PredictionResponse>

    @GET("/history/cek-kesehatan")
    fun getHistory(): Call<HistoryResponse>

    @GET("/articles/getAllArticles")
    fun getAllArticles(): Call<ResponseArticle>
}