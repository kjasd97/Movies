package com.ulyanenko.movies.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ulyanenko.movies.R
import com.ulyanenko.movies.data.Trailer
import com.ulyanenko.movies.presentation.util.TrailerDiffCallback

class TrailersAdapter : ListAdapter<Trailer, TrailersAdapter.TrailersViewHolder>(TrailerDiffCallback()) {


    lateinit var trailersOnClickListener: TrailersOnClickListener


    fun setTrailersOnClick(trailersOnClickListener: TrailersOnClickListener){
        this.trailersOnClickListener = trailersOnClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.trailer_item,
            parent, false)
        return TrailersViewHolder(view)
    }


    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
    val trailer = getItem(position)
        holder.textViewTrailerName.text = trailer.name

        holder.itemView.setOnClickListener {
            if (trailersOnClickListener != null) {
            trailersOnClickListener.trailersOnClick(trailer)
            }
        }
    }


    class TrailersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewTrailerName: TextView = view.findViewById(R.id.textViewTrailerName)

    }

    interface TrailersOnClickListener{
        fun trailersOnClick(trailer: Trailer)
    }

}