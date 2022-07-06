package com.shannon.moviemvvm.data.repository

import com.shannon.moviemvvm.data.MovieDetailEntity
import com.shannon.moviemvvm.data.remote.TheMovieDBInterface
import javax.inject.Inject

class DetailRepository  @Inject constructor(private val apiService: TheMovieDBInterface)  {
    suspend fun getMovieDetail(movieId: Int) : MovieDetailEntity {
        return apiService.getMovieDetails(movieId)
    }
}