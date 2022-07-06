package com.shannon.moviemvvm.domain.usecase

import com.shannon.moviemvvm.data.repository.DetailRepository
import com.shannon.moviemvvm.domain.dto.MovieDetailDto
import com.shannon.moviemvvm.domain.mapper.MovieDetailMapper
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: DetailRepository,
    private val mapper: MovieDetailMapper
    ) {
    suspend fun invoke(movieId: Int): MovieDetailDto {
        val detail = repository.getMovieDetail(movieId)
        return mapper.map(detail)
    }
}