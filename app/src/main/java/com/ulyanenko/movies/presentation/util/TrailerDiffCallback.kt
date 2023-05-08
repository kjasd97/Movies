package com.ulyanenko.movies.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.ulyanenko.movies.data.Trailer

class TrailerDiffCallback:DiffUtil.ItemCallback<Trailer>() {
    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem==newItem
    }
}