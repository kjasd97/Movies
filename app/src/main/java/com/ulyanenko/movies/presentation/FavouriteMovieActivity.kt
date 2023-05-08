package com.ulyanenko.movies.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulyanenko.movies.R
import com.ulyanenko.movies.data.Movie
import com.ulyanenko.movies.databinding.ActivityFavouriteMovieBinding

class FavouriteMovieActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFavouriteMovieBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerViewFavouriteMovies: RecyclerView =
            binding.recyclerViewFavouriteMovies
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
            movieAdapter.submitList(it)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FavouriteMovieActivity::class.java)
        }
    }
}