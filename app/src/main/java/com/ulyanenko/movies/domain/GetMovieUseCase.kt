package com.ulyanenko.movies.domain

import androidx.lifecycle.LiveData
import com.ulyanenko.movies.data.Movie

class GetMovieUseCase  (private val movieRepository: MovieRepository) {

     fun getMovie(id:Int): LiveData<Movie>{
        return movieRepository.getMovie(id)
    }

}