package com.example.news.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.util.Parameter
import com.example.news.model.NewResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    val newResponse = MutableLiveData<NewResponse>()
    val newsError = MutableLiveData<Boolean>()
    val newsLoading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        News()
    }

    fun News() {
        disposable.add(
            com.example.news.network.News.getService().getNews(Parameter.getCountry(),1)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewResponse>() {
                    override fun onSuccess(t: NewResponse) {
                        newResponse.value = t
                        //newResponse.value=disposable.add(com.example.news.network.News.getService().getNewsDetail())

                        newsError.value = false
                        newsLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.d("internet Error", "İnternet Bağlantınızı kontrol ediniz")
                        newsError.value = true
                        newsLoading.value = true
                    }
                }
                ))
    }

}


