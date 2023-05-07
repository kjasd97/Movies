package com.ulyanenko.movies

import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("docs") val movie: MutableList<Movie>) {
}