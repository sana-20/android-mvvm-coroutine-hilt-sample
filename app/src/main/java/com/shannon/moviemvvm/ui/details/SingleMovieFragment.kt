package com.shannon.moviemvvm.ui.details

import androidx.navigation.fragment.navArgs
import com.shannon.moviemvvm.data.model.MovieDetails
import com.shannon.moviemvvm.data.repository.NetworkState
import com.shannon.moviemvvm.databinding.FragmentSingleMovieBinding
import com.shannon.moviemvvm.ui.BaseFragment
import com.shannon.moviemvvm.utils.observe
import com.shannon.moviemvvm.utils.visible
import com.shannon.moviemvvm.viewmodel.SingleMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SingleMovieFragment : BaseFragment<FragmentSingleMovieBinding>() {

    private val viewModel: SingleMovieViewModel by viewModel()

    override fun getViewBinding(): FragmentSingleMovieBinding  = FragmentSingleMovieBinding.inflate(layoutInflater)

    override fun initView() {
        val safeArgs: SingleMovieFragmentArgs by navArgs()
        val movieId = safeArgs.movieId
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