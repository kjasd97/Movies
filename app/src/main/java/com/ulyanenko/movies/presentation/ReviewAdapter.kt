package com.ulyanenko.movies.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ulyanenko.movies.R
import com.ulyanenko.movies.data.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private var reviews: MutableList<Review> = mutableListOf()

    fun setReviews(reviews: MutableList<Review>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.review_item,
            parent,
            false
        )
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]

        holder.textViewAuthor.text = review.author
        holder.textViewReview.text = review.review

        val type = review.type
        val colorResId = when (type) {
            "Позитивный" -> {
                android.R.color.holo_green_light
            }
            "Негативный" -> {
                android.R.color.holo_red_light
            }
            else -> {
                android.R.color.holo_orange_light
            }
        }
        val color = ContextCompat.getColor(holder.itemView.context, colorResId)
        holder.linearLayoutReviewHolder.setBackgroundColor(color)
    }


    class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val linearLayoutReviewHolder =
            view.findViewById<LinearLayout>(R.id.linearLayoutReviewHolder)
        val textViewAuthor = view.findViewById<TextView>(R.id.textViewAuthor)
        val textViewReview = view.findViewById<TextView>(R.id.textViewReview)
    }
}