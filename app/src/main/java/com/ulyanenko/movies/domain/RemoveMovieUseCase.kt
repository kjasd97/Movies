package com.ulyanenko.movies.domain

import io.reactivex.rxjava3.core.Completable

class RemoveMovieUseCase(private val movieRepository: MovieRepository) {

    fun deleteMovie(id:Int):Completable{
       return movieRepository.deleteMovie(id)
    }

}