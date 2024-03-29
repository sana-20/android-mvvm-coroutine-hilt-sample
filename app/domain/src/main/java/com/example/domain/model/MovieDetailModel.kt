package com.example.domain.model

data class MovieDetailModel(
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
