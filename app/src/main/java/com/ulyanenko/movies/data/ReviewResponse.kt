package com.ulyanenko.movies.data

import com.google.gson.annotations.SerializedName
import com.ulyanenko.movies.data.Review

data class ReviewResponse(@SerializedName("docs") val reviews: MutableList <Review>) {
}