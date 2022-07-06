package com.shannon.moviemvvm.domain.dto

data class MovieDetailDto(
    val budget: Int,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val tagline: String,
    val title: String,
    val rating: Double
)
