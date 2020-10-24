package com.example.bookmyshow.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.bookmyshow.R
import com.example.bookmyshow.Utility.NetworkHelper
import com.example.bookmyshow.adapter.MoviesRecyclerviewAdapter
import com.example.bookmyshow.database.MovieDatabase
import com.example.bookmyshow.model.Movie
import com.example.bookmyshow.repository.HomeRepositoryImpl
import com.example.bookmyshow.retrofit.RetrofitClientBuilder
import com.example.bookmyshow.viewModel.HomeViewModel
import com.example.bookmyshow.viewModel.HomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    val TAG = "HomeFragment"
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        observeViewModel()

    }

    private fun setUpViewModel() {
        showProgressBar()

        homeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(
                NetworkHelper(requireContext()),
                HomeRepositoryImpl(
                    RetrofitClientBuilder.buildMovieService(),
                    MovieDatabase.getInstance(requireContext()).movieDao()
                )
            )
        )[HomeViewModel::class.java]
        homeViewModel.onCreate()
    }

    private fun observeViewModel() {
        homeViewModel.movieResponse.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observeViewModel: " + it.results)
            showMovies(it.results)
            hideProgressBar()
        })

        homeViewModel.errorResponse.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observeViewModel: dd")
            showErrorMessage(it)
            hideProgressBar()
        })
    }


    private fun showMovies(movies: List<Movie>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = MoviesRecyclerviewAdapter(movies)
    }

    private fun showErrorMessage(error: String) {
        errorView.text = error
        errorView.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}