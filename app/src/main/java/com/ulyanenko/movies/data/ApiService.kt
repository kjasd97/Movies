package com.ulyanenko.movies.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie?token=058Y1TD-T4R4DFA-KZ7VBYQ-9R1R3XP&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    suspend fun loadMovies(@Query("page") page:Int):MovieResponse

    @GET("movie?token=058Y1TD-T4R4DFA-KZ7VBYQ-9R1R3XP&field=id")
    suspend fun loadTrailers(@Query("search") id: Int):TrailerResponse

    @GET("review?token=058Y1TD-T4R4DFA-KZ7VBYQ-9R1R3XP&field=movieId")
    suspend fun loadReviews(@Query("search") id: Int):ReviewResponse
}