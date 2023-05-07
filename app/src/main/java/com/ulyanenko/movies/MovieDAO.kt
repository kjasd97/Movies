package com.ulyanenko.movies

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable

@Dao
interface MovieDAO {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM favourite_movies where id = :movieId")
    fun getFavouriteMovie(movieId: Int): LiveData<Movie>

    @Insert
    fun insertMovie(movie: Movie): Completable

    @Query("Delete FROM favourite_movies where id = :movieId")
    fun removeMovie(movieId: Int): Completable


}