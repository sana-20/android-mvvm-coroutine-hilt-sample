package com.shannon.moviemvvm.data

import androidx.annotation.Keep

@Keep
data class MoviesEntity(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) {
    @Keep
    data class Result(
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
    )
}
