package com.ulyanenko.movies

import com.google.gson.annotations.SerializedName

data class TrailersList(@SerializedName("trailers") val trailers:MutableList<Trailer>) {
}