package com.ulyanenko.movies.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.ulyanenko.movies.domain.MovieRepository
import io.reactivex.rxjava3.core.Completable

class MovieRepositoryImpl (application: Application):MovieRepository {

    private val movieDAO = MovieDataBase.getInstance(application).methodsMovieDao()

    override suspend fun addMovie(movie: Movie) {
      movieDAO.insertMovie(movie)
    }

    override fun getMovies(): LiveData<List<Movie>> {
      return movieDAO.getAllFavouriteMovies()
    }

    override fun getMovie(id: Int): LiveData<Movie> {
       return movieDAO.getFavouriteMovie(id)
    }

    override suspend fun deleteMovie(id: Int){
      movieDAO.removeMovie(id)
    }
}