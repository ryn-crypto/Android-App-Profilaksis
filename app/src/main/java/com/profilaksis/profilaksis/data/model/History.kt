package com.profilaksis.profilaksis.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class HistoryResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<HistoryData>
)

data class HistoryData(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("prediction_result")
    val predictionResult: Float,
    @SerializedName("type")
    val type: String,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("description")
    val description: String,
    @SerializedName("accuracy")
    val accuracy: Float
)