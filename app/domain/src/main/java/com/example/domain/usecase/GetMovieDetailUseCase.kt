package com.example.domain.usecase

import com.example.data.repository.DetailRepository
import com.example.domain.model.MovieDetailModel
import com.example.domain.mapper.MovieDetailMapper
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: DetailRepository,
    private val mapper: MovieDetailMapper
    ) {
    suspend fun invoke(movieId: Int): MovieDetailModel {
        val detail = repository.getMovieDetail(movieId)
        return mapper.map(detail)
    }
}