package com.ulyanenko.movies

import android.app.Application
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _trailers = MutableLiveData<MutableList<Trailer>>()
    val trailers: LiveData<MutableList<Trailer>>
        get() = _trailers

    private val _reviews = MutableLiveData<MutableList<Review>>()
    val reviews: LiveData<MutableList<Review>>
        get() = _reviews

    val movieDAO: MovieDAO = MovieDataBase.getInstance(application).methodsMovieDao()

    val compositeDisposable = CompositeDisposable()



    fun getFavouriteMovie(id: Int): LiveData<Movie> {
        return movieDAO.getFavouriteMovie(id)
    }

    fun insertMovie(movie: Movie){
        val disposable = movieDAO.insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun removeMovie(movieId: Int){
        val disposable = movieDAO.removeMovie(movieId)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }


    fun loadTrailers(id: Int) {
        val disposable = ApiFactory.apiService.loadTrailers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { t -> t.trailersList.trailers }
            .subscribe({
                _trailers.value = it
            },
                {
                    Log.d("bad", it.toString())
                })
        compositeDisposable.add(disposable)
    }

    fun loadReviews(id: Int) {
        val disposable = ApiFactory.apiService.loadReviews(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { rewr -> rewr.reviews }
            .subscribe({
                _reviews.value = it
            },
                {
                    Log.d("Load Review Error", it.toString())
                })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}
