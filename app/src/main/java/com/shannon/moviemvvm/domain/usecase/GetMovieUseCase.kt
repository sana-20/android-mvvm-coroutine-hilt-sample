package com.shannon.moviemvvm.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shannon.moviemvvm.data.repository.MovieRepository
import com.shannon.moviemvvm.domain.dto.MovieDto
import com.shannon.moviemvvm.domain.mapper.MoviesMapper
import com.shannon.moviemvvm.utils.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase  @Inject constructor(
    private val movieRepository: MovieRepository,
    private val moviesMapper: MoviesMapper
) {
    fun invoke(): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviePagingSource(movieRepository, moviesMapper)
            }
        ).flow
    }
}
