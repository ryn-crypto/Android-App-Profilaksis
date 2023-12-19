package com.profilaksis.profilaksis.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PredictionResponse(

	@field:SerializedName("data")
	val data: ResultData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultData(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("healthStatus")
	val healthStatus: String? = null,

	@field:SerializedName("prediction")
	val prediction: Float? = null,

	@field:SerializedName("date")
	val date: Date? = null
)
