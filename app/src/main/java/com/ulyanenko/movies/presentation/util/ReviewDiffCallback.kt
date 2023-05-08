package com.ulyanenko.movies.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.ulyanenko.movies.data.Review

class ReviewDiffCallback:DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem==newItem
    }
}