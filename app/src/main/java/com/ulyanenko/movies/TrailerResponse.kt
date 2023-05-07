package com.ulyanenko.movies

import com.google.gson.annotations.SerializedName

data class TrailerResponse(@SerializedName("videos") val trailersList: TrailersList) {
}