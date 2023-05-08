package com.ulyanenko.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulyanenko.movies.R
import com.ulyanenko.movies.data.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var progressBarLoading: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        moviesAdapter = MoviesAdapter()
        recyclerView.adapter = moviesAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        mainViewModel.movies.observe(this) {
            moviesAdapter.submitList(it)
        }

        mainViewModel.isLoading.observe(this) {
            if (it) {
                progressBarLoading.visibility = View.VISIBLE
            } else {
                progressBarLoading.visibility = View.GONE
            }
        }

        moviesAdapter.setOnReachEndListener(object : MoviesAdapter.OnReachEndListener {
            override fun onReachEnd() {
                mainViewModel.loadMovies()
            }
        })

        moviesAdapter.setOnMovieClickListener(object : MoviesAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                val intent = MovieDetailActivity.newIntent(this@MainActivity, movie)
                startActivity(intent)
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(item.itemId== R.id.favMovies){
           val intent = FavouriteMovieActivity.newIntent(this)
           startActivity(intent)
       }
        return super.onOptionsItemSelected(item)
    }

   private fun init(){
        mainViewModel  = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView= findViewById(R.id.moviesHolder)
        progressBarLoading= findViewById(R.id.progressBarLoading)
        mainViewModel.loadMovies()

    }
}