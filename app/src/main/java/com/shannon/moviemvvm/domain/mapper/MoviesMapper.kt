package com.shannon.moviemvvm.domain.mapper

import com.shannon.moviemvvm.data.MoviesEntity
import com.shannon.moviemvvm.domain.dto.MovieDto
import javax.inject.Inject

class MoviesMapper @Inject constructor() {
    fun map(from: List<MoviesEntity.Result>): List<MovieDto> {
        return from.map {
            mapToMovie(it)
        }
    }

    private fun mapToMovie(result: MoviesEntity.Result): MovieDto {
        return MovieDto(
            id = result.id,
            posterPath = result.poster_path,
            releaseDate = result.release_date,
            title = result.title
        )
    }
}