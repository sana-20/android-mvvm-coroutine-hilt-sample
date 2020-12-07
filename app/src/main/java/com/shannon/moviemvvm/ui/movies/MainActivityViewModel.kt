package com.shannon.moviemvvm.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.shannon.moviemvvm.utils.Constants.POST_PER_PAGE
import com.shannon.moviemvvm.data.repository.MovieSource
import com.shannon.moviemvvm.data.repository.MovieSourceFactory
import com.shannon.moviemvvm.data.repository.NetworkState
import com.shannon.moviemvvm.data.repository.Repository
import com.shannon.moviemvvm.data.model.Movie
import com.shannon.moviemvvm.ui.BaseViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivityViewModel(private val repository: Repository) : BaseViewModel() {
    var moviePagedList: LiveData<PagedList<Movie>>

    private val movieSourceFactory = MovieSourceFactory(repository, compositeDisposable)

    init {
        moviePagedList = (LivePagedListBuilder(movieSourceFactory, PagedListThreadPoolProvider.providePagedListConfig(POST_PER_PAGE, POST_PER_PAGE))).
        setFetchExecutor(PagedListThreadPoolProvider.provideThreadPool(5)).build()
    }

    object PagedListThreadPoolProvider {
        fun providePagedListConfig(initialLoadSize : Int, pageSize : Int) : PagedList.Config = PagedList.Config.Builder().
        setEnablePlaceholders(true).
        setInitialLoadSizeHint(initialLoadSize).
        setPageSize(pageSize).
        build()
        fun provideThreadPool(threadCount : Int) : ExecutorService = Executors.newFixedThreadPool(threadCount)
    }



    val  networkState : LiveData<NetworkState> by lazy {
        Transformations.switchMap<MovieSource, NetworkState>(
            movieSourceFactory.moviesLiveDataSource, MovieSource::networkState)
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


}