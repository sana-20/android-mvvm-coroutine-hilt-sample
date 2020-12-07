package com.shannon.moviemvvm.data.repository

import com.shannon.moviemvvm.data.api.TheMovieDBInterface
import com.shannon.moviemvvm.data.model.MovieDetails
import com.shannon.moviemvvm.data.model.MovieResponse
import io.reactivex.Single

interface Repository {
    fun fetchDetailTest(movieId: Int): Single<MovieDetails>
    fun fetchListTest(page: Int): Single<MovieResponse>
}

class RepositoryImpl(private val apiService: TheMovieDBInterface) : Repository {

    override fun fetchListTest(page: Int): Single<MovieResponse> {
        return apiService.getPopularMovie(page)
    }


    override fun fetchDetailTest(
        movieId: Int
    ): Single<MovieDetails> {
        return apiService.getMovieDetails(movieId)
    }


}
