package com.ulyanenko.movies.domain

import androidx.lifecycle.LiveData
import com.ulyanenko.movies.data.Movie

class GetMoviesUseCase (private val movieRepository: MovieRepository) {

    fun getMovies(): LiveData<List<Movie>> {
    return movieRepository.getMovies()
    }

}