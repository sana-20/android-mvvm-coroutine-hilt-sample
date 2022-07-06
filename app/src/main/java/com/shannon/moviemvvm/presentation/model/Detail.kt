package com.shannon.moviemvvm.presentation.model

data class Detail(
    val id: Int,
    val budget: String,
    val overview: String,
    val imageUrl: String,
    val releaseDate: String,
    val revenue: String,
    val runtime: Int,
    val tagline: String,
    val title: String,
    val rating: String
)
