package com.ulyanenko.movies.domain

import com.ulyanenko.movies.data.Movie

class AddMovieUseCase (private val movieRepository: MovieRepository) {

    fun addMovie (movie:Movie){
        movieRepository.addMovie(movie)
    }

}