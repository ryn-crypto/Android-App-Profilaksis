package com.profilaksis.profilaksis.data.model

import com.google.gson.annotations.SerializedName

data class ResponseArticle(

    @field:SerializedName("data")
    val data: List<ResponseArticleItem?>? = null,

    @field:SerializedName("message")
    val message: String? = null,
)

data class ResponseArticleItem(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("tags")
    val tags: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("source_url")
    val sourceUrl: String? = null,

    @field:SerializedName("author")
    val author: String? = null,
)
