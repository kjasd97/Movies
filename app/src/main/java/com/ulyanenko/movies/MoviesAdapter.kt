package com.ulyanenko.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = mutableListOf()
    private lateinit var onReachEndListener: OnReachEndListener
    private lateinit var onMovieClickListener: OnMovieClickListener

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener) {
        this.onReachEndListener = onReachEndListener
    }

    fun setOnMovieClickListener(onMovieClickListener: OnMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener
    }

    fun setMovie(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item, parent, false
        )
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        Glide.with(holder.itemView)
            .load(movie.poster.url)
            .into(holder.imageViewPoster)

        val rating: Double = movie.rating.kp
        val backGroundId = if (rating > 7) {
            R.drawable.circle_green
        } else if (rating > 5) {
            R.drawable.circle_orange
        } else {
            R.drawable.circle_red
        }

        val backGround = ContextCompat.getDrawable(holder.itemView.context, backGroundId)
        holder.textViewRating.background = backGround
        holder.textViewRating.text =  String.format("%.1f", rating)

        if (position == movies.size - 10 && onReachEndListener != null) {
            onReachEndListener.onReachEnd()
        }

        holder.itemView.setOnClickListener {
            if (onMovieClickListener != null) {
                onMovieClickListener.onMovieClick(movie)
            }
        }
    }

    interface OnReachEndListener {

        fun onReachEnd()

    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageViewPoster: ImageView = view.findViewById(R.id.poster)
        val textViewRating: TextView = view.findViewById(R.id.rating)


    }
}