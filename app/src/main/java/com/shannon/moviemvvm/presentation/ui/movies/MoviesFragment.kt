package com.shannon.moviemvvm.presentation.ui.movies

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.shannon.moviemvvm.databinding.FragmentMoviesBinding
import com.shannon.moviemvvm.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(){

    private val moviesViewModel: MoviesViewModel by activityViewModels()

    private lateinit var adapter: MoviesAdapter

    private var job: Job? = null

    override fun initView() {

        adapter = MoviesAdapter(::onClick)

        binding.apply {
            rvMovieList.layoutManager = GridLayoutManager(requireContext(), 3)
            rvMovieList.setHasFixedSize(true)
            rvMovieList.adapter = adapter
        }

        getMovies()
    }

    private fun getMovies() {
        job?.cancel()
        job = lifecycleScope.launch {
            moviesViewModel.getMovies().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun getViewBinding(): FragmentMoviesBinding =
        FragmentMoviesBinding.inflate(layoutInflater)

    override fun observeChange() {
    }

    private fun onClick(movieId: Int) {
        val action =
            MoviesFragmentDirections.actionMovieListFragmentToSingleMovieFragment(movieId = movieId)
        findNavController().navigate(action)
    }

}