package com.profilaksis.profilaksis.data

import android.util.Log
import com.google.gson.Gson
import com.profilaksis.profilaksis.data.model.HistoryData
import com.profilaksis.profilaksis.data.model.LoginResponse
import com.profilaksis.profilaksis.data.model.RegisterResponse
import com.profilaksis.profilaksis.data.model.ResponseArticleItem
import com.profilaksis.profilaksis.data.model.ResponseResult
import com.profilaksis.profilaksis.data.model.UserData
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.data.remote.ApiConfig
import com.profilaksis.profilaksis.data.remote.requestdata.LoginRequestBody
import com.profilaksis.profilaksis.data.remote.requestdata.RegisterRequestBody
import com.profilaksis.profilaksis.utils.parseErrorMessage
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Repository {

    suspend fun registerUser(dataRegister: RegisterRequestBody): RegisterResponse {
        val requestBody = Gson().toJson(dataRegister).toRequestBody("application/json".toMediaTypeOrNull())

        return suspendCoroutine { continuation ->
            val client = ApiConfig.getApiService().registerUser(requestBody)
            client.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        val responseData = RegisterResponse(token = registerResponse?.token, message = "no error")
                        continuation.resume(responseData)
                    } else {
                        val errorBody = response.errorBody()?.string()
                        if (errorBody != null) {
                            val errorMessage = parseErrorMessage(errorBody)
                            val responseData = RegisterResponse(message = errorMessage)
                            continuation.resume(responseData)
                        } else {
                            val responseData = RegisterResponse(message = "Unknown error")
                            continuation.resume(responseData)
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    val responseData = RegisterResponse(message = "Failure: ${t.message}")
                    continuation.resume(responseData)
                }
            })
        }
    }


    suspend fun loginUser(dataLogin: LoginRequestBody): LoginResponse {
        val requestBody = Gson().toJson(dataLogin).toRequestBody("application/json".toMediaTypeOrNull())

        return suspendCoroutine { continuation ->
            val client = ApiConfig.getApiService().loginUser(requestBody)
            client.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        continuation.resume(loginResponse!!)
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let { parseErrorMessage(it) }
                        val responseData = LoginResponse(message = errorMessage)
                        continuation.resume(responseData)
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    val responseData = LoginResponse(message = "Failure: ${t.message}")
                    continuation.resume(responseData)
                }
            })
        }
    }

    fun getLastHistory(): HistoryData {
        return HistoryData(
            "1",
            "username",
            90f,
            "Hearth",
            java.util.Date(),
            "description",
            0.5f
        )
    }

    fun getProfile(): UserData {
        return UserData(
            id = "1",
            username = "Riyan",
            avatar = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            token = " ",
        )
    }

    fun sendData(): ResponseResult {
        return ResponseResult(
            prediction = 90f,
            message = "Hearth",
            date = Date(),
            userName = "Riyan",
            description = "you have hearth disease with 90% probability of certainty and you need to go to the doctor immediately"
        )
    }

    fun getArticle(): List<ResponseArticleItem> {
        return listOf(
            ResponseArticleItem(
                updatedAt = "2021-10-04T09:00:00.000Z",
                imageUrl = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = Date(),
                id = 1,
                title = "How to get a good night's sleep",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. Donec euismod, nisl vitae aliquam ultricies, nunc nisl ultricies nunc, vitae aliquam nisl nisl vitae nisl. ",
                tags = "sleep"
            ),
            ResponseArticleItem(
                updatedAt = "2021-10-04T09:00:00.000Z",
                imageUrl = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = Date(),
                id = 2,
                title = "How to get a good night's sleep",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod",
                tags = "sleep"
            ),
            ResponseArticleItem(
                updatedAt = "2021-10-04T09:00:00.000Z",
                imageUrl = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = Date(),
                id = 3,
                title = "How to get a good night's sleep",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod",
                tags = "sleep"
            ),
            ResponseArticleItem(
                updatedAt = "2021-10-04T09:00:00.000Z",
                imageUrl = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                createdAt = Date(),
                id = 4,
                title = "How to get a good night's sleep",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod",
                tags = "sleep"
            ),
        )
    }

    companion object {
        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }

        @Volatile
        private var instance: Repository? = null
    }
}