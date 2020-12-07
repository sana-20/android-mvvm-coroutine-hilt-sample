package com.shannon.moviemvvm.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.shannon.moviemvvm.data.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieSourceFactory(private val repository: Repository,
                         private val compositeDisposable: CompositeDisposable) :
    DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource = MutableLiveData<MovieSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieSource(repository, compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}