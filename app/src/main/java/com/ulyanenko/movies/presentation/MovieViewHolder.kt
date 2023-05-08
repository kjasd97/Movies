package com.ulyanenko.movies.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulyanenko.movies.R

class MovieViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val imageViewPoster: ImageView = view.findViewById(R.id.poster)
    val textViewRating: TextView = view.findViewById(R.id.rating)

}