package com.ulyanenko.movies.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.ulyanenko.movies.domain.MovieRepository
import io.reactivex.rxjava3.core.Completable

class MovieRepositoryImpl (application: Application):MovieRepository {

    private val movieDAO = MovieDataBase.getInstance(application).methodsMovieDao()

    override fun addMovie(movie: Movie):Completable {
      return movieDAO.insertMovie(movie)
    }

    override fun getMovies(): LiveData<List<Movie>> {
      return movieDAO.getAllFavouriteMovies()
    }

    override fun getMovie(id: Int): LiveData<Movie> {
       return movieDAO.getFavouriteMovie(id)
    }

    override fun deleteMovie(id: Int):Completable {
      return movieDAO.removeMovie(id)
    }
}