package com.ulyanenko.movies.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ulyanenko.movies.data.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {


    abstract fun methodsMovieDao(): MovieDAO

    companion object {

        private var movieDataBase: MovieDataBase? = null

        fun getInstance(application: Application): MovieDataBase {
            movieDataBase?.let {
                return it
            }

            val db = Room.databaseBuilder(
                application,
                MovieDataBase::class.java,
                "movie_db"
            ).build()
            movieDataBase = db
            return movieDataBase as MovieDataBase
        }

    }

}