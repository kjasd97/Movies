package com.ulyanenko.movies.domain

import androidx.lifecycle.LiveData
import com.ulyanenko.movies.data.Movie
import io.reactivex.rxjava3.core.Completable

interface MovieRepository {

   suspend fun addMovie(movie:Movie)

   fun getMovies ():LiveData<List<Movie>>

   fun getMovie(id:Int):LiveData<Movie>

    suspend fun deleteMovie(id:Int)

}