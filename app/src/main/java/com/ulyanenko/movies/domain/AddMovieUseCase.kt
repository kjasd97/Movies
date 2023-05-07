package com.ulyanenko.movies.domain

import com.ulyanenko.movies.data.Movie
import io.reactivex.rxjava3.core.Completable

class AddMovieUseCase (private val movieRepository: MovieRepository) {

    fun addMovie (movie:Movie):Completable{
       return movieRepository.addMovie(movie)
    }

}