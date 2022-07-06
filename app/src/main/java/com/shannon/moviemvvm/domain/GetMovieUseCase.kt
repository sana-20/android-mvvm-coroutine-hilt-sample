package com.shannon.moviemvvm.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shannon.moviemvvm.data.Movie
import com.shannon.moviemvvm.data.repository.MoviePagingSource
import com.shannon.moviemvvm.data.repository.MovieRepository
import com.shannon.moviemvvm.utils.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase  @Inject constructor(private val movieRepository: MovieRepository) {
    fun invoke(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviePagingSource(movieRepository)
            }
        ).flow
    }
}
