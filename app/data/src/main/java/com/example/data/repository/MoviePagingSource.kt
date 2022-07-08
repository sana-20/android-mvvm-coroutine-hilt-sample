package com.example.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.dto.MoviesDto
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

private const val ITEM_STARTING_PAGE_INDEX = 1
private const val MAX_PAGE_COUNT = 15

class MoviePagingSource(private val movieRepository: MovieRepository) :
    PagingSource<Int, MoviesDto.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDto.Result> {
        return try {
            val page = params.key ?: ITEM_STARTING_PAGE_INDEX

            val result: List<MoviesDto.Result> = withContext(Dispatchers.IO) {
                val deferredMovies : Deferred<MoviesDto> = async {
                    movieRepository.getMovies(page)
                }

                val movies = deferredMovies.await().results

                movies
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

    override fun getRefreshKey(state: PagingState<Int, MoviesDto.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
