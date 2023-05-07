package com.ulyanenko.movies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavouriteMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movie)

        val recyclerViewFavouriteMovies: RecyclerView =
            findViewById(R.id.recyclerViewFavouriteMovies)
        val movieAdapter = MoviesAdapter()
        recyclerViewFavouriteMovies.layoutManager = GridLayoutManager(this, 2)
        recyclerViewFavouriteMovies.adapter = movieAdapter

        movieAdapter.setOnMovieClickListener(object : MoviesAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                val intent = MovieDetailActivity.newIntent(
                    this@FavouriteMovieActivity,
                    movie
                )
                startActivity(intent)
            }
        })

        val favouriteMoviesViewModel =
            ViewModelProvider(this).get(FavouriteMoviesViewModel::class.java)

        favouriteMoviesViewModel.getMovies().observe(this) {
            movieAdapter.setMovie(it)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FavouriteMovieActivity::class.java)
        }
    }
}