package com.ulyanenko.movies

import com.google.gson.annotations.SerializedName
import com.ulyanenko.movies.data.Trailer

data class TrailersList(@SerializedName("trailers") val trailers:MutableList<Trailer>) {
}