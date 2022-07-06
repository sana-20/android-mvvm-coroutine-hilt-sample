package com.shannon.moviemvvm.data.remote

import com.shannon.moviemvvm.data.entity.MovieDetailEntity
import com.shannon.moviemvvm.data.entity.MoviesEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    // https://api.themoviedb.org/3/movie/popular?api_key=6e63c2317fbe963d76c3bdc2b785f6d1&page=1
    // https://api.themoviedb.org/3/movie/299534?api_key=6e63c2317fbe963d76c3bdc2b785f6d1
    // https://api.themoviedb.org/3/

    @GET("movie/popular")
    suspend fun getMovies(@Query("page") page: Int): MoviesEntity

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): MovieDetailEntity

}