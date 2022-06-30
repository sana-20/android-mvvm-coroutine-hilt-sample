package com.shannon.moviemvvm.ui.movies

import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.shannon.moviemvvm.data.model.Movie
import com.shannon.moviemvvm.data.repository.NetworkState
import com.shannon.moviemvvm.databinding.FragmentMovieListBinding
import com.shannon.moviemvvm.ui.BaseFragment
import com.shannon.moviemvvm.utils.observe
import com.shannon.moviemvvm.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : BaseFragment<FragmentMovieListBinding>(),
    PopularMoviePagedListAdapter.Listener {

    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var movieAdapter : PopularMoviePagedListAdapter

    override fun initView() {
        movieAdapter = PopularMoviePagedListAdapter(this)

        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.MOVIE_VIEW_TYPE) return 1
                else return 3
            }
        }

        binding.apply {
            rvMovieList.layoutManager = gridLayoutManager
            rvMovieList.setHasFixedSize(true)
            rvMovieList.adapter = movieAdapter
        }
    }

    override fun getViewBinding(): FragmentMovieListBinding = FragmentMovieListBinding.inflate(layoutInflater)

    override fun observeChange() {
        observe(viewModel.moviePagedList, ::onDataLoaded)
        observe(viewModel.networkState, ::handleLoading)
    }

    private fun handleLoading(status: NetworkState) {
        binding.progressBarPopular.visibility =
            if (viewModel.listIsEmpty() && status == NetworkState.LOADING) View.VISIBLE else View.GONE
        binding.txtErrorPopular.visibility =
            if (viewModel.listIsEmpty() && status == NetworkState.ERROR) View.VISIBLE else View.GONE

        if (!viewModel.listIsEmpty()) {
            movieAdapter.setNetworkState(status)
        }
    }

    private fun onDataLoaded(items: PagedList<Movie>) {
        movieAdapter.submitList(items)
    }

    override fun onSingleMovieClicked(movieId: Int?) {
        movieId?.let {
            val fragment = SingleMovieFragment(movieId)

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit()
        }
    }


}