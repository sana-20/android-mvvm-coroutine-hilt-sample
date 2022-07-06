package com.shannon.moviemvvm.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shannon.moviemvvm.data.Movie
import com.shannon.moviemvvm.utils.Constants.ITEM_STARTING_PAGE_INDEX
import com.shannon.moviemvvm.utils.Constants.MAX_PAGE_COUNT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MoviePagingSource(private val movieRepository: MovieRepository) :
    PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: ITEM_STARTING_PAGE_INDEX

            val result: List<Movie> = withContext(Dispatchers.IO) {
                val deferredMovies = async {
                    movieRepository.getMovies(page)
                }
                val movies = deferredMovies.await().movieList

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

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
