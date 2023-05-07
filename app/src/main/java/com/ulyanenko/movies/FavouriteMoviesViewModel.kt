package com.ulyanenko.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FavouriteMoviesViewModel(application: Application): AndroidViewModel(application) {

    val movieDao = MovieDataBase.getInstance(application).methodsMovieDao()

    fun getMovies():LiveData<List<Movie>>{
        return movieDao.getAllFavouriteMovies()
    }

}