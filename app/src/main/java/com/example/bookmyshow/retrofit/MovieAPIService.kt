package com.example.bookmyshow.retrofit

import com.example.bookmyshow.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {

    @GET("discover/movie")
    fun getMovies(@Query("api_key") key: String): Call<MovieResponse>

}