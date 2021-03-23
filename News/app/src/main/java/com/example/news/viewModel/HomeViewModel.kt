package com.example.news.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.model.Article
import com.example.news.model.NewResponse
import com.example.news.util.Constants.Companion.API_KEY
import com.example.news.util.Constants.Companion.PAGE
import com.example.news.util.Constants.Companion.PAGESİZE
import com.example.news.util.getCountry

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    val newResponse = MutableLiveData<List<Article>>()
    val newsError = MutableLiveData<Boolean>()
    val newsLoading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
   // private val pageSize = 10
   //private lateinit var sourceFactory: NewsDataSourceFactory

    fun refreshData() {
        news(getCountry().toString(), API_KEY, PAGE, PAGESİZE)

    }
    fun news(country: String, apiKey: String,page:Int,pageSize:Int) {
        disposable.add(
            com.example.news.network.News.getService().getNews(country,apiKey,pageSize,page)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewResponse>() {
                    override fun onSuccess(t: NewResponse) {
                        newResponse.value = t.articles
                        newsError.value = false
                        newsLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        newsError.value = true
                        newsLoading.value = true
                    }
                }
                ))

      /* sourceFactory = NewsDataSourceFactory(
            disposable,
            News.getService(),
            country = country,
            apiKey = apiKey
        )

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setEnablePlaceholders(false)
            .build()

        val eventPagedList = RxPagedListBuilder(sourceFactory, config)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()
        newsLoading.value = true

        disposable.add(eventPagedList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe { newsLoading.value = true }
            .subscribe({
                if (it.isNotEmpty()) {
                    newResponse.value=it
                    newsError.value = false
                    newsLoading.value = false
                }
            }, {

                newsError.value = true
                newsLoading.value = false

            })
        )*/



    }


}