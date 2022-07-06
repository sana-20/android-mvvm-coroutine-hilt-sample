package com.shannon.moviemvvm.ui.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.shannon.moviemvvm.data.MovieDetails
import com.shannon.moviemvvm.data.NetworkState
import com.shannon.moviemvvm.databinding.FragmentDetailMovieBinding
import com.shannon.moviemvvm.ui.BaseFragment
import com.shannon.moviemvvm.utils.observe
import com.shannon.moviemvvm.utils.visible

class MovieDetailFragment : BaseFragment<FragmentDetailMovieBinding>() {

    private val detailViewModel: MovieDetailViewModel by activityViewModels()

    override fun getViewBinding(): FragmentDetailMovieBinding  = FragmentDetailMovieBinding.inflate(layoutInflater)

    override fun initView() {
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        val movieId = safeArgs.movieId

        detailViewModel.getMovieDetail(movieId)
    }

    override fun observeChange() {
        observe(detailViewModel.items, ::onItemLoaded)
    }

    private fun onItemLoaded(movieDetails: MovieDetails) {
        binding.movieDetails = movieDetails
    }

    private fun handleLoading(status: NetworkState) {
        binding.progressBar.visible(status == NetworkState.LOADING)
        binding.txtError.visible(status == NetworkState.ERROR)
    }

}