package com.shannon.moviemvvm.data.entity

import androidx.annotation.Keep

@Keep
data class MovieDetailEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Long,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    @Keep
    data class BelongsToCollection(
        val backdrop_path: String,
        val id: Int,
        val name: String,
        val poster_path: String
    )

    @Keep
    data class Genre(
        val id: Int,
        val name: String
    )

    @Keep
    data class ProductionCompany(
        val id: Int,
        val logo_path: String,
        val name: String,
        val origin_country: String
    )

    @Keep
    data class ProductionCountry(
        val iso_3166_1: String,
        val name: String
    )

    @Keep
    data class SpokenLanguage(
        val english_name: String,
        val iso_639_1: String,
        val name: String
    )
}