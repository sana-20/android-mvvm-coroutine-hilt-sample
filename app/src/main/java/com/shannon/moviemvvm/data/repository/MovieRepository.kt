package com.shannon.moviemvvm.data.repository

import com.shannon.moviemvvm.data.MovieResponse
import com.shannon.moviemvvm.data.remote.TheMovieDBInterface
import javax.inject.Inject

class MovieRepository  @Inject constructor(private val apiService: TheMovieDBInterface) {
    suspend fun getMovies(page: Int) : MovieResponse {
        return apiService.getMovies(page)
    }
}