package com.shannon.moviemvvm.domain.mapper

import com.shannon.moviemvvm.data.MovieDetailEntity
import com.shannon.moviemvvm.domain.dto.MovieDetailDto
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() {
    fun map(from: MovieDetailEntity) : MovieDetailDto {
        return MovieDetailDto(
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