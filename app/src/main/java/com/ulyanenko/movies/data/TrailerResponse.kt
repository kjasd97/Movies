package com.ulyanenko.movies.data

import com.google.gson.annotations.SerializedName
import com.ulyanenko.movies.TrailersList

data class TrailerResponse(@SerializedName("videos") val trailersList: TrailersList) {
}