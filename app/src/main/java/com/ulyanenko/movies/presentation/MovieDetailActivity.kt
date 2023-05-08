package com.ulyanenko.movies.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulyanenko.movies.R
import com.ulyanenko.movies.data.Movie
import com.ulyanenko.movies.data.Trailer
import com.ulyanenko.movies.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater)
    }


    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var trailersAdapter: TrailersAdapter
    private lateinit var reviewAdapter: ReviewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

        trailersAdapter = TrailersAdapter()
        binding.recycleViewTrailers.adapter = trailersAdapter

        reviewAdapter = ReviewAdapter()
       binding.recycleViewReviews.adapter = reviewAdapter

        val movie = intent.getSerializableExtra("movie") as Movie

        Glide.with(this).load(movie.poster.url).into( binding.imageViewPoster)
        binding.textViewTitle.text = movie.name
        val year = movie.year
        binding.textViewYear.text = "$year"
        binding.textViewDescription.text = movie.description

        movieDetailViewModel.loadTrailers(movie.id)
        movieDetailViewModel.trailers.observe(this) {
            trailersAdapter.submitList(it)
        }

        movieDetailViewModel.loadReviews(movie.id)
        movieDetailViewModel.reviews.observe(this) {
            reviewAdapter.submitList(it)
        }

        trailersAdapter.setTrailersOnClick(object : TrailersAdapter.TrailersOnClickListener {
            override fun trailersOnClick(trailer: Trailer) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(trailer.url))
                startActivity(intent)
            }
        })

        val starOff = ContextCompat.getDrawable(
            this@MovieDetailActivity,
            android.R.drawable.star_big_off
        )
        val starOn = ContextCompat.getDrawable(
            this@MovieDetailActivity,
            android.R.drawable.star_big_on
        )

        movieDetailViewModel.getFavouriteMovie(movie.id).observe(this) {
            if (it == null) {
                binding.imageViewStar.setImageDrawable(starOff)
                binding.imageViewStar.setOnClickListener {
                    movieDetailViewModel.insertMovie(movie)
                }
            } else {
                binding.imageViewStar.setImageDrawable(starOn)
                binding.imageViewStar.setOnClickListener {
                    movieDetailViewModel.removeMovie(movie.id)
                }
            }
        }

    }


    fun init() {
        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
    }


    companion object {
        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie", movie)
            return intent
        }
    }
}