package com.ulyanenko.movies.presentation

import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
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
import com.ulyanenko.movies.data.receiver.MyReceiver
import com.ulyanenko.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private val receiver = MyReceiver()


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

        moviesAdapter = MoviesAdapter()
        binding.moviesHolder.adapter = moviesAdapter
        binding.moviesHolder.layoutManager = GridLayoutManager(this, 2)

        mainViewModel.movies.observe(this) {
            moviesAdapter.submitList(it)
        }

        mainViewModel.isLoading.observe(this) {
            if (it) {
                binding.progressBarLoading.visibility = View.VISIBLE
            } else {
                binding.progressBarLoading.visibility = View.GONE
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


        val intentFilter = IntentFilter().apply {
             addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        }
        registerReceiver(receiver, intentFilter)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favMovies) {
            val intent = FavouriteMovieActivity.newIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.loadMovies()

    }
}