package com.example.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.repository.MoviePagingSource
import com.example.data.repository.MovieRepository
import com.example.domain.entity.MoviesEntity
import com.example.domain.mapper.MoviesMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 20

class GetMovieUseCase  @Inject constructor(
    private val movieRepository: MovieRepository,
    private val moviesMapper: MoviesMapper
) {
    fun invoke(): Flow<PagingData<MoviesEntity>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviePagingSource(movieRepository)
            }
        ).flow
            .map {
                it.map { result ->
                    moviesMapper.mapToMovie(result)
                }
            }
    }
}
