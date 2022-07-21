package com.example.domain.mapper

import com.example.common.Constants
import com.example.data.dto.MoviesDto
import com.example.domain.model.MoviesModel
import javax.inject.Inject

class MoviesMapper @Inject constructor() {
    fun mapToMovie(from: MoviesDto.Result): MoviesModel {
        return MoviesModel(
            id = from.id,
            imageUrl = Constants.POSTER_BASE_URL + from.poster_path,
            releaseDate = from.release_date,
            title = from.title
        )
    }
}