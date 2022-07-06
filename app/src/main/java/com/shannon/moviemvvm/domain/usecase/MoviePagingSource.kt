package com.shannon.moviemvvm.domain.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shannon.moviemvvm.data.MoviesEntity
import com.shannon.moviemvvm.data.repository.MovieRepository
import com.shannon.moviemvvm.domain.dto.MovieDto
import com.shannon.moviemvvm.domain.mapper.MoviesMapper
import com.shannon.moviemvvm.utils.Constants.ITEM_STARTING_PAGE_INDEX
import com.shannon.moviemvvm.utils.Constants.MAX_PAGE_COUNT
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MoviePagingSource(private val movieRepository: MovieRepository, private val moviesMapper: MoviesMapper) :
    PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        return try {
            val page = params.key ?: ITEM_STARTING_PAGE_INDEX

            val result: List<MovieDto> = withContext(Dispatchers.IO) {
                val deferredMovies : Deferred<MoviesEntity> = async {
                    movieRepository.getMovies(page)
                }

                val movies = deferredMovies.await()

                moviesMapper.map(movies.results)
            }

            LoadResult.Page(
                data = result,
                prevKey = if (page == ITEM_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == MAX_PAGE_COUNT) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
