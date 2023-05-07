package com.ulyanenko.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ulyanenko.movies.data.ApiFactory
import com.ulyanenko.movies.data.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private var page: Int = 0

    private val _movies = MutableLiveData<MutableList<Movie>>()
    val movies: LiveData<MutableList<Movie>>
        get() = _movies

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun loadMovies() {
        val loading =_isLoading.value
        if (loading == true){
            return
        }

        val disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.value = true
            }
            .doAfterTerminate {
                _isLoading.value = false
            }
            .subscribe({

                val loadedMovies = _movies.value
                if (loadedMovies != null) {
                    loadedMovies.addAll(it.movie)
                    _movies.value = loadedMovies
                } else {
                    _movies.value = it.movie
                }
                page++
            },
                object : Consumer<Throwable> {
                    override fun accept(t: Throwable) {
                        Log.d("MainViewModel", t.toString())
                    }

                })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}