package com.ulyanenko.movies

import com.google.gson.annotations.SerializedName

data class ReviewResponse(@SerializedName("docs") val reviews: MutableList <Review>) {
}