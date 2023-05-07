package com.ulyanenko.movies.data

import com.google.gson.annotations.SerializedName

data class Trailer(@SerializedName("name") val name:String, @SerializedName("url") val url:String) {
}