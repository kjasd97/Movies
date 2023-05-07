package com.ulyanenko.movies

import com.google.gson.annotations.SerializedName

data class Rating(@SerializedName("kp") val kp:Double):java.io.Serializable {
}