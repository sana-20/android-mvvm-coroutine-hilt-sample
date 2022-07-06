package com.shannon.moviemvvm.presentation.ui.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.shannon.moviemvvm.databinding.FragmentDetailMovieBinding
import com.shannon.moviemvvm.domain.dto.MovieDetailDto
import com.shannon.moviemvvm.presentation.ui.BaseFragment
import com.shannon.moviemvvm.utils.Constants
import com.shannon.moviemvvm.utils.observe
import java.text.NumberFormat
import java.util.*

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

    private fun onItemLoaded(movieDetails: MovieDetailDto) {
        with(binding) {
            Glide.with(ivMoviePoster)
                .load(Constants.POSTER_BASE_URL + movieDetails.posterPath)
                .into(ivMoviePoster)

            movieTitle.text = movieDetails.title
            movieTagline.text = movieDetails.tagline
            movieReleaseDate.text = movieDetails.releaseDate
            movieRating.text = movieDetails.rating.toString()
            movieRuntime.text = "${movieDetails.runtime} minutes"

            val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
            movieBudget.text = formatCurrency.format(movieDetails.budget)
            movieRevenue.text = formatCurrency.format(movieDetails.revenue)
            movieOverview.text = movieDetails.overview
        }
    }

}