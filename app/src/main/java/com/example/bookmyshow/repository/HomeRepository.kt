package com.example.bookmyshow.repository

import com.example.bookmyshow.model.MovieResponse

interface HomeRepository {

    fun getMoviesFromRemote(
        apiKey: String,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesFromLocal(
        onSuccess: (MovieResponse?) -> Unit
    )

}