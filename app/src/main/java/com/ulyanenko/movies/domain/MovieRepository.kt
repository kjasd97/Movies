package com.ulyanenko.movies.domain

import androidx.lifecycle.LiveData
import com.ulyanenko.movies.data.Movie
import io.reactivex.rxjava3.core.Completable

interface MovieRepository {

    fun addMovie(movie:Movie):Completable

    fun getMovies ():LiveData<List<Movie>>

    fun getMovie(id:Int):LiveData<Movie>

    fun deleteMovie(id:Int):Completable

}