package com.shannon.moviemvvm.data.repository

import com.shannon.moviemvvm.data.MovieDetails
import com.shannon.moviemvvm.data.remote.TheMovieDBInterface
import javax.inject.Inject

class DetailRepository  @Inject constructor(private val apiService: TheMovieDBInterface)  {
    suspend fun getMovieDetail(movieId: Int) : MovieDetails {
        return apiService.getMovieDetails(movieId)
    }
}