package com.example.domain.usecase

import com.example.data.repository.DetailRepository
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.mapper.MovieDetailMapper
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: DetailRepository,
    private val mapper: MovieDetailMapper
    ) {
    suspend fun invoke(movieId: Int): MovieDetailEntity {
        val detail = repository.getMovieDetail(movieId)
        return mapper.map(detail)
    }
}