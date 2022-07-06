package com.shannon.moviemvvm.data.repository

import com.shannon.moviemvvm.data.MoviesEntity
import com.shannon.moviemvvm.data.remote.TheMovieDBInterface
import javax.inject.Inject

class MovieRepository  @Inject constructor(private val apiService: TheMovieDBInterface) {
    suspend fun getMovies(page: Int) : MoviesEntity {
        return apiService.getMovies(page)
    }
}