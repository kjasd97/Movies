package com.ulyanenko.movies.data

import com.google.gson.annotations.SerializedName

data class Poster(@SerializedName("url") val url:String):java.io.Serializable {
}