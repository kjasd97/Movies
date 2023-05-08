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
import kotlinx.coroutines.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var page: Int = 0

    private val _movies = MutableLiveData<MutableList<Movie>>()
    val movies: LiveData<MutableList<Movie>>
        get() = _movies

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadMovies() {

        val loading = _isLoading.value
        if (loading == true) {
            return
        }

        _isLoading.value = true

        scope.launch {
            val response = ApiFactory.apiService.loadMovies(page)
            val loadedMovies = _movies.value
            withContext(Dispatchers.Main) {
                if (loadedMovies != null) {
                    loadedMovies.addAll(response.movie)

                } else {
                    _movies.value = response.movie
                }
                _isLoading.value = false
            }
            page++
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}





