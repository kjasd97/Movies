package com.ulyanenko.movies.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.ulyanenko.movies.data.Movie

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
      return oldItem==newItem
    }
}