package com.example.data.repository

import com.example.data.dto.MovieDetailDto
import com.example.data.remote.TheMovieDBInterface
import javax.inject.Inject

class DetailRepository  @Inject constructor(private val apiService: TheMovieDBInterface)  {
    suspend fun getMovieDetail(movieId: Int) : MovieDetailDto {
        return apiService.getMovieDetails(movieId)
    }
}