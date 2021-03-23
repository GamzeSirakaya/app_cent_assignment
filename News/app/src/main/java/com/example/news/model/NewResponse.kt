package com.example.news.model

import androidx.room.Entity


data class NewResponse(

    val articles:MutableList<Article>,
    val status: String?,
    val totalResults: Int?
)


