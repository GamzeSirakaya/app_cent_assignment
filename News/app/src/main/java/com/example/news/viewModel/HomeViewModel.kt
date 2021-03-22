package com.example.news.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.model.Article
import com.example.news.model.NewResponse
import com.example.news.util.getCountry
import com.example.news.util.getLanguage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    val newResponse = MutableLiveData<List<Article>>()
    val newsError = MutableLiveData<Boolean>()
    val newsLoading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    init {
        news()
    }

    fun news() {
        disposable.add(
            com.example.news.network.News.getService().getNews(getCountry().toString())
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
    }


}