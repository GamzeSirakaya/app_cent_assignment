package com.example.news.model

data class NewResponse (
    val article :List<Article>,
                          val status:String,
                          val totalResults :Int)
