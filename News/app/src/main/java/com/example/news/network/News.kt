package com.example.news.network

import com.example.news.util.Constants
import com.example.news.util.Constants.Companion.API_KEY
import com.example.news.model.NewResponse
import com.example.news.util.Constants.Companion.PAGE
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
        @Query("page") page: Int= PAGE,
        @Query("apiKey") apiKey: String= API_KEY,


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