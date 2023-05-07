package com.ulyanenko.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ulyanenko.movies.data.MovieDataBase
import com.ulyanenko.movies.data.*
import com.ulyanenko.movies.domain.AddMovieUseCase
import com.ulyanenko.movies.domain.GetMovieUseCase
import com.ulyanenko.movies.domain.RemoveMovieUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepositoryImpl = MovieRepositoryImpl(application)

    private val getMovie = GetMovieUseCase(repository)
    private val addMovie = AddMovieUseCase(repository)
    private val deleteMovie = RemoveMovieUseCase(repository)

    private val _trailers = MutableLiveData<MutableList<Trailer>>()
    val trailers: LiveData<MutableList<Trailer>>
        get() = _trailers

    private val _reviews = MutableLiveData<MutableList<Review>>()
    val reviews: LiveData<MutableList<Review>>
        get() = _reviews

    val compositeDisposable = CompositeDisposable()


    fun getFavouriteMovie(id: Int): LiveData<Movie> {
        return getMovie.getMovie(id)
    }

    fun insertMovie(movie: Movie) {
        val disposable = addMovie.addMovie(movie)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun removeMovie(movieId: Int) {
        val disposable = deleteMovie.deleteMovie(movieId)
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
