package com.ulyanenko.movies.domain

class RemoveMovieUseCase(private val movieRepository: MovieRepository) {

    fun deleteMovie(id:Int){
        movieRepository.deleteMovie(id)
    }

}