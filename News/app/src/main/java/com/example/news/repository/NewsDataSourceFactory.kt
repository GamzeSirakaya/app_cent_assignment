package com.example.news.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.cars.repository.NewsDataSource
import com.example.news.model.Article
import com.example.news.network.News
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(private val disposable: CompositeDisposable,
                            private val newsAPI: News,
                            private val country: String,
                            private val apiKey: String

) : DataSource.Factory<Int, Article>() {


    private val newsLiveDataSource = MutableLiveData<NewsDataSource>()
    override fun create(): DataSource<Int, Article> {
        val newsdataSource = NewsDataSource(newAPI= newsAPI,disposable = disposable,country=country,apiKey=apiKey )

        newsLiveDataSource.postValue(newsdataSource)
        return newsdataSource
    }
}