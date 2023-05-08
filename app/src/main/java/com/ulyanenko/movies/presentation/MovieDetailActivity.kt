package com.ulyanenko.movies.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulyanenko.movies.R
import com.ulyanenko.movies.data.Movie
import com.ulyanenko.movies.data.Trailer

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var imageViewPoster: ImageView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewYear: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var recyclerViewTrailersHolder: RecyclerView
    private lateinit var trailersAdapter: TrailersAdapter
    private lateinit var recyclerViewReviewHolder: RecyclerView
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var imageViewStarr: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initViews()

        trailersAdapter = TrailersAdapter()
        recyclerViewTrailersHolder.adapter = trailersAdapter

        reviewAdapter = ReviewAdapter()
        recyclerViewReviewHolder.adapter = reviewAdapter

        val movie = intent.getSerializableExtra("movie") as Movie

        Glide.with(this).load(movie.poster.url).into(imageViewPoster)
        textViewTitle.text = movie.name
        val year = movie.year
        textViewYear.text = "$year"
        textViewDescription.text = movie.description

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
                imageViewStarr.setImageDrawable(starOff)
                imageViewStarr.setOnClickListener {
                    movieDetailViewModel.insertMovie(movie)
                }
            } else {
                imageViewStarr.setImageDrawable(starOn)
                imageViewStarr.setOnClickListener {
                    movieDetailViewModel.removeMovie(movie.id)
                }
            }
        }

    }


    fun initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewYear = findViewById(R.id.textViewYear)
        textViewDescription = findViewById(R.id.textViewDescription)
        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        recyclerViewTrailersHolder = findViewById(R.id.recycleViewTrailers)
        recyclerViewReviewHolder = findViewById(R.id.recycleViewReviews)
        imageViewStarr = findViewById(R.id.imageViewStar)
    }


    companion object {
        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie", movie)
            return intent
        }
    }
}