package com.ulyanenko.movies.domain

import androidx.lifecycle.LiveData
import com.ulyanenko.movies.data.Movie

interface MovieRepository {

    fun addMovie(movie:Movie)

    fun getMovies ():LiveData<List<Movie>>

    fun getMovie(id:Int):LiveData<Movie>

    fun deleteMovie(id:Int)

}