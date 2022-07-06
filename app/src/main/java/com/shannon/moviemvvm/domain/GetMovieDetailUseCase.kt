package com.shannon.moviemvvm.domain

import com.shannon.moviemvvm.data.MovieDetails
import com.shannon.moviemvvm.data.repository.DetailRepository
import javax.inject.Inject

class GetMovieDetailUseCase  @Inject constructor (private val repository: DetailRepository) {
    suspend fun invoke(movieId: Int): MovieDetails {
        return repository.getMovieDetail(movieId)
    }
}