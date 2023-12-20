package com.profilaksis.profilaksis.data

import android.util.Log
import com.google.gson.Gson
import com.profilaksis.profilaksis.data.model.HistoryResponse
import com.profilaksis.profilaksis.data.model.LoginResponse
import com.profilaksis.profilaksis.data.model.PredictionResponse
import com.profilaksis.profilaksis.data.model.RegisterResponse
import com.profilaksis.profilaksis.data.model.ResponseArticle
import com.profilaksis.profilaksis.data.model.ResultsItem
import com.profilaksis.profilaksis.data.model.UserData
import com.profilaksis.profilaksis.data.remote.ApiConfig
import com.profilaksis.profilaksis.data.remote.requestdata.LoginRequestBody
import com.profilaksis.profilaksis.data.remote.requestdata.PredictRequestBody
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
        val requestBody =
            Gson().toJson(dataRegister).toRequestBody("application/json".toMediaTypeOrNull())

        return suspendCoroutine { continuation ->
            val client = ApiConfig.getApiService().registerUser(requestBody)
            client.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>,
                ) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        val responseData =
                            RegisterResponse(token = registerResponse?.token, message = "no error")
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
        val requestBody =
            Gson().toJson(dataLogin).toRequestBody("application/json".toMediaTypeOrNull())

        return suspendCoroutine { continuation ->
            val client = ApiConfig.getApiService().loginUser(requestBody)
            client.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>,
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

    suspend fun getAllArticle(): ResponseArticle {
        val client = ApiConfig.getApiService().getAllArticles()

        return suspendCoroutine { continuation ->
            client.enqueue(object : Callback<ResponseArticle> {
                override fun onResponse(
                    call: Call<ResponseArticle>,
                    response: Response<ResponseArticle>,
                ) {
                    if (response.isSuccessful) {
                        val articleResponse = response.body()
                        continuation.resume(articleResponse!!)
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let { parseErrorMessage(it) }
                        val responseData = ResponseArticle(message = errorMessage)
                        continuation.resume(responseData)
                    }
                }

                override fun onFailure(call: Call<ResponseArticle>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }
            })
        }
    }

    suspend fun sendData(
        dataRequest: PredictRequestBody,
        token: String,
        type: String,
    ): PredictionResponse {

        val requestBody =
            Gson().toJson(dataRequest).toRequestBody("application/json".toMediaTypeOrNull())

        val client =
            when (type) {
                "diabetes" -> ApiConfig.getApiServiceWithAuth(token).predictDiabetes(requestBody)
                else -> ApiConfig.getApiServiceWithAuth(token).predictHeart(requestBody)
            }

        Log.e("test123", client.request().toString())

        return suspendCoroutine { continuation ->
            client.enqueue(object : Callback<PredictionResponse> {
                override fun onResponse(
                    call: Call<PredictionResponse>,
                    response: Response<PredictionResponse>,
                ) {
                    if (response.isSuccessful) {
                        val resultData = response.body()
                        Log.e("test123", "response $response")
                        Log.e("test123", "response ${response.body()}")
                        continuation.resume(resultData!!)
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let { parseErrorMessage(it) }
                        val responseData = PredictionResponse(message = errorMessage)
                        continuation.resume(responseData)
                    }
                }

                override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                    val responseData = PredictionResponse(message = "Failure: ${t.message}")
                    continuation.resume(responseData)
                }
            })
        }
    }

    suspend fun getHistory(token: String): HistoryResponse {
        val client = ApiConfig.getApiServiceWithAuth(token).getHistory()

        return suspendCoroutine { continuation ->
            client.enqueue(object : Callback<HistoryResponse> {
                override fun onResponse(
                    call: Call<HistoryResponse>,
                    response: Response<HistoryResponse>,
                ) {
                    if (response.isSuccessful) {
                        val resultData = response.body()
                        continuation.resume(resultData!!)
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = errorBody?.let { parseErrorMessage(it) }
                        val responseData = HistoryResponse(message = errorMessage)
                        continuation.resume(responseData)
                    }
                }

                override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                    val responseData = HistoryResponse(message = "Failure: ${t.message}")
                    continuation.resume(responseData)
                }
            })
        }
    }


    fun getLastHistory(): ResultsItem {
        return ResultsItem(
            id = 1,
            username = "Riyan",
            predictionResult = 1f,
            healthStatus = "Sehat",
            createdAt = Date(),
            kategoriPenyakit = "Tidak ada",
            keterangan = "Tidak ada",
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