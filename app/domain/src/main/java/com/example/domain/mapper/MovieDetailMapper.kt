package com.example.domain.mapper

import com.example.data.dto.MovieDetailDto
import com.example.domain.model.MovieDetailModel
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() {
    fun map(from: MovieDetailDto) : MovieDetailModel {
        return MovieDetailModel(
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