package com.example.data.repository

import com.example.data.dto.MoviesDto
import com.example.data.remote.TheMovieDBInterface
import javax.inject.Inject

class MovieRepository  @Inject constructor(private val apiService: TheMovieDBInterface) {
    suspend fun getMovies(page: Int) : MoviesDto {
        return apiService.getMovies(page)
    }
}