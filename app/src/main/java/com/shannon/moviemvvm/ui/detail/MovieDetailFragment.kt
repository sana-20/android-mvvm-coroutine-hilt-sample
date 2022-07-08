package com.shannon.moviemvvm.ui.detail

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.shannon.moviemvvm.databinding.FragmentDetailMovieBinding
import com.shannon.moviemvvm.model.Detail
import com.shannon.moviemvvm.state.DetailViewState
import com.shannon.moviemvvm.ui.BaseFragment
import com.shannon.moviemvvm.utils.event
import com.shannon.moviemvvm.utils.observe
import com.shannon.moviemvvm.utils.viewVisible

class MovieDetailFragment : BaseFragment<FragmentDetailMovieBinding>() {

    private val detailViewModel: MovieDetailViewModel by activityViewModels()

    override fun getViewBinding(): FragmentDetailMovieBinding =
        FragmentDetailMovieBinding.inflate(layoutInflater)

    override fun initView() {
        binding.ivMoviePoster.setOnClickListener {
            detailViewModel.onClickImage()
        }

        fetchData()
    }

    private fun fetchData() {
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        val movieId = safeArgs.movieId
        detailViewModel.getMovieDetail(movieId)
    }

    override fun observeChange() {
        with(detailViewModel){
            observe(detailViewState){
                when(it){
                    is DetailViewState.Data -> renderData(it.data)
                    is DetailViewState.Error -> {

                    }
                    is DetailViewState.Loading -> {


                    }
                }
            }

            event(loadingEvent) {
                binding.progressBar.viewVisible(it)
            }

            event(toastEvent) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderData(detail: Detail) {
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