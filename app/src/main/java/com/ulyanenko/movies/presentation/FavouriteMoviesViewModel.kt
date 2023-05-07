package com.ulyanenko.movies.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ulyanenko.movies.data.MovieDataBase
import com.ulyanenko.movies.data.Movie

class FavouriteMoviesViewModel(application: Application): AndroidViewModel(application) {

    val movieDao = MovieDataBase.getInstance(application).methodsMovieDao()

    fun getMovies():LiveData<List<Movie>>{
        return movieDao.getAllFavouriteMovies()
    }

}