package com.example.bookmyshow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmyshow.Utility.NetworkHelper
import com.example.bookmyshow.constant.Constant
import com.example.bookmyshow.model.MovieResponse
import com.example.bookmyshow.repository.HomeRepository

class HomeViewModel(
    private val networkHelper: NetworkHelper,
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _movieResponse = MutableLiveData<MovieResponse>()
    val movieResponse: LiveData<MovieResponse>
        get() = _movieResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String>
        get() = _errorResponse

    fun onCreate() {

        if (networkHelper.isNetworkConnected()) {
            homeRepository.getMoviesFromRemote(Constant.API_KEY, { movieResponse ->
                _movieResponse.postValue(movieResponse)
            }, { error ->
                _errorResponse.postValue(Constant.SOMETHING_WENT_WRONG)
            })
        } else {
            homeRepository.getMoviesFromLocal { movieResponse ->
                if (movieResponse != null && movieResponse.results.isNotEmpty()) {
                    _movieResponse.postValue(movieResponse)
                } else {
                    _errorResponse.postValue(Constant.SOMETHING_WENT_WRONG)
                }
            }
        }
    }


}