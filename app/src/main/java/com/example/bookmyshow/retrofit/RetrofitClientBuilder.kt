package com.example.bookmyshow.retrofit

import com.example.bookmyshow.constant.Constant
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientBuilder {

    private val client = OkHttpClient.Builder().build()

    private val gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()


    private val retrofit = Retrofit.Builder()
        .baseUrl(Constant.MOVIE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun buildMovieService(): MovieAPIService {
        return retrofit.create(MovieAPIService::class.java)
    }
}