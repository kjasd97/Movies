package com.ulyanenko.movies.data

import com.google.gson.annotations.SerializedName
import com.ulyanenko.movies.data.Movie

data class MovieResponse(@SerializedName("docs") val movie: MutableList<Movie>) {
}