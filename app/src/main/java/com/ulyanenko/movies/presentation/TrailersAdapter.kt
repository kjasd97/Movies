package com.ulyanenko.movies.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulyanenko.movies.R
import com.ulyanenko.movies.data.Trailer

class TrailersAdapter : RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>() {

    private var trailers: MutableList<Trailer> = mutableListOf()

    lateinit var trailersOnClickListener: TrailersOnClickListener

    fun setTrailers(trailers: MutableList<Trailer>) {
        this.trailers = trailers
        notifyDataSetChanged()
    }

    fun setTrailersOnClick(trailersOnClickListener: TrailersOnClickListener){
        this.trailersOnClickListener = trailersOnClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.trailer_item,
            parent, false)
        return TrailersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
    val trailer = trailers[position]
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