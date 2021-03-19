package com.example.news.network

import com.example.news.Util.Constants
import com.example.news.Util.Constants.Companion.API_KEY
import com.example.news.Util.Constants.Companion.BASE_URL
import com.example.news.model.Article
import com.example.news.model.NewResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface News {
    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String= API_KEY,


    ):Single<NewResponse>

    @GET("everything")
    fun getNewsDetail(
        @Query("q") keyword: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String

    ):Single<NewResponse>

    companion object {
        const val BASE_URL = Constants.BASE_URL
        fun getService(): News {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(News::class.java)
        }
    }
}