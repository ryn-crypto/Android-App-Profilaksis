package com.profilaksis.profilaksis.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class HistoryResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("results")
    val results: List<ResultsItem?>? = null,
)

data class ResultsItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("prediction_result")
    val predictionResult: Float? = null,

    @field:SerializedName("health_status")
    val healthStatus: String? = null,


    @field:SerializedName("created_at")
    val createdAt: Date? = null,


    @field:SerializedName("kategori_penyakit")
    val kategoriPenyakit: String? = null,

    @field:SerializedName("keterangan")
    val keterangan: String? = null,


    )
