package com.example.bookmyshow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookmyshow.Utility.NetworkHelper
import com.example.bookmyshow.repository.HomeRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val networkHelper: NetworkHelper,
    private val homeRepository: HomeRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(networkHelper, homeRepository) as T
    }
}