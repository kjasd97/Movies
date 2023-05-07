package com.ulyanenko.movies.domain

import io.reactivex.rxjava3.core.Completable

class RemoveMovieUseCase(private val movieRepository: MovieRepository) {

    suspend fun deleteMovie(id:Int){
       movieRepository.deleteMovie(id)
    }

}