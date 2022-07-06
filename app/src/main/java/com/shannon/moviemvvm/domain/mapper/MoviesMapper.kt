package com.shannon.moviemvvm.domain.mapper

import com.shannon.moviemvvm.data.entity.MoviesEntity
import com.shannon.moviemvvm.domain.dto.Movie
import com.shannon.moviemvvm.utils.Constants
import javax.inject.Inject

class MoviesMapper @Inject constructor() {
    fun map(from: List<MoviesEntity.Result>): List<Movie> {
        return from.map {
            mapToMovie(it)
        }
    }

    private fun mapToMovie(result: MoviesEntity.Result): Movie {
        return Movie(
            id = result.id,
            imageUrl = Constants.POSTER_BASE_URL + result.poster_path,
            releaseDate = result.release_date,
            title = result.title
        )
    }
}