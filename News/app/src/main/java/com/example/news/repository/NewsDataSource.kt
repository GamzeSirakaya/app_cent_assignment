package com.example.cars.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.news.model.Article
import com.example.news.network.News

import io.reactivex.disposables.CompositeDisposable

class NewsDataSource(
    private val disposable: CompositeDisposable,
    private val newAPI: News,
    private val country: String,
    private val apiKey: String

) : PageKeyedDataSource<Int, Article>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        val numberOfItems = params.requestedLoadSize
        createObservable(0, 1, numberOfItems, callback, null)

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Article>
    ) {

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Article>
    ) {
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        createObservable(page, page + 1, numberOfItems, null, callback)
    }

    private fun createObservable(
        requestedPage: Int,
        adjacentPage: Int,
        requestedLoadSize: Int,
        initialcallback: LoadInitialCallback<Int, Article>?,
        callback: LoadCallback<Int, Article>?
    ) {
        disposable.add(newAPI.getNews(
            country = country,
            apiKey = apiKey,
            requestedLoadSize,
            requestedPage

        ).subscribe(
            { response ->
                initialcallback?.onResult(response.articles, null, adjacentPage)
                callback?.onResult(response.articles, adjacentPage)

            },
            {
            }
        ))
    }
}