package com.shannon.moviemvvm.mapper

import com.example.domain.entity.MovieDetailEntity
import com.shannon.moviemvvm.model.Detail
import com.example.common.Constants
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class DetailMapper @Inject constructor() {
    fun mapToDetail(from: MovieDetailEntity) : Detail {
        return Detail(
            id = from.id,
            budget = formatCurrency(from.budget.toLong()),
            overview = from.overview,
            imageUrl = Constants.POSTER_BASE_URL + from.posterPath,
            releaseDate = from.releaseDate,
            revenue = formatCurrency(from.revenue),
            runtime = from.runtime,
            tagline = from.tagline,
            title = from.title,
            rating = from.rating.toString()
        )
    }

    private fun formatCurrency(value: Long) : String{
        return NumberFormat.getCurrencyInstance(Locale.US).format(value)
    }
}