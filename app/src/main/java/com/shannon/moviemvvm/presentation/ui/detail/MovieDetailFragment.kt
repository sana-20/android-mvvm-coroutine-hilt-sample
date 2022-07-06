package com.shannon.moviemvvm.presentation.ui.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.shannon.moviemvvm.databinding.FragmentDetailMovieBinding
import com.shannon.moviemvvm.presentation.model.Detail
import com.shannon.moviemvvm.presentation.ui.BaseFragment
import com.shannon.moviemvvm.utils.observe

class MovieDetailFragment : BaseFragment<FragmentDetailMovieBinding>() {

    private val detailViewModel: MovieDetailViewModel by activityViewModels()

    override fun getViewBinding(): FragmentDetailMovieBinding =
        FragmentDetailMovieBinding.inflate(layoutInflater)

    override fun initView() {
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        val movieId = safeArgs.movieId

        detailViewModel.getMovieDetail(movieId)
    }

    override fun observeChange() {
        observe(detailViewModel.items, ::onItemLoaded)
    }

    private fun onItemLoaded(detail: Detail) {
        with(binding) {
            Glide.with(ivMoviePoster)
                .load(detail.imageUrl)
                .into(ivMoviePoster)

            movieTitle.text = detail.title
            movieTagline.text = detail.tagline
            movieReleaseDate.text = detail.releaseDate
            movieRating.text = detail.rating
            movieRuntime.text = "${detail.runtime} minutes"

            movieBudget.text = detail.budget
            movieRevenue.text = detail.revenue
            movieOverview.text = detail.overview
        }
    }

}