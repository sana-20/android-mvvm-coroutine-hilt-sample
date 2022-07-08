package com.example.domain.mapper

import com.example.data.dto.MovieDetailDto
import com.example.domain.entity.MovieDetailEntity
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() {
    fun map(from: MovieDetailDto) : MovieDetailEntity {
        return MovieDetailEntity(
            from.budget,
            from.id,
            from.overview,
            from.poster_path,
            from.release_date,
            from.revenue,
            from.runtime,
            from.tagline,
            from.title,
            from.vote_average
        )
    }
}