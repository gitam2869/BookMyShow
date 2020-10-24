package com.example.bookmyshow.repository

import com.example.bookmyshow.dao.MovieDao
import com.example.bookmyshow.model.MovieResponse
import com.example.bookmyshow.retrofit.MovieAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepositoryImpl(
    private val movieAPIService: MovieAPIService,
    private val movieDao: MovieDao
) : HomeRepository {

    override fun getMoviesFromRemote(
        apiKey: String,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieAPIService.getMovies(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    Thread {
                        movieDao.insertMovies(response.body()!!)
                        onSuccess(response.body()!!)
                    }.start()
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onFailure(t.localizedMessage ?: "Something went wrong")

            }
        })
    }

    override fun getMoviesFromLocal(onSuccess: (MovieResponse?) -> Unit) {
        Thread {
            onSuccess(movieDao.getMovies())
        }.start()
    }
}