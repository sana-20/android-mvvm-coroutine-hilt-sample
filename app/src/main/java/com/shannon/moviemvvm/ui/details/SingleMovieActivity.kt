package com.shannon.moviemvvm.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.shannon.moviemvvm.R
import com.shannon.moviemvvm.data.model.Movie
import com.shannon.moviemvvm.data.model.MovieDetails
import com.shannon.moviemvvm.data.repository.NetworkState
import com.shannon.moviemvvm.databinding.ActivitySingleMovieBinding
import com.shannon.moviemvvm.ui.BaseActivity
import com.shannon.moviemvvm.utils.observe
import com.shannon.moviemvvm.utils.visible
import org.koin.android.viewmodel.ext.android.viewModel

class SingleMovieActivity : BaseActivity() {

    private val viewModel: SingleMovieViewModel by viewModel()
    private lateinit var binding: ActivitySingleMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_movie)

        val movieId: Int = intent.getIntExtra("id", 1)
        viewModel.fetchMovieDetails(movieId)
    }

    override fun observeChange() {
        observe(viewModel.downloadedMovieResponse, ::onDataLoaded)
        observe(viewModel.networkState, ::handleLoading)
    }


    private fun handleLoading(status: NetworkState) {
        binding.progressBar.visible(status == NetworkState.LOADING)
        binding.txtError.visible(status == NetworkState.ERROR)
    }

    private fun onDataLoaded(items: MovieDetails) {
        binding.movieDetails = items
    }


}
