package com.ulyanenko.movies.data

import com.google.gson.annotations.SerializedName

data class Rating(@SerializedName("kp") val kp:Double):java.io.Serializable {
}