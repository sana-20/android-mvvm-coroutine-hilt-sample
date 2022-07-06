package com.shannon.moviemvvm.ui.movies

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shannon.moviemvvm.databinding.MovieListItemBinding
import com.shannon.moviemvvm.domain.dto.MovieDto
import com.shannon.moviemvvm.utils.Constants

class MovieViewHolder(private val binding: MovieListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieDto, onClick: (movieId: Int) -> Unit) {
        binding.cvMovieTitle.text = movie.title
        binding.cvMovieReleaseDate.text = movie.releaseDate

        Glide.with(binding.cvIvMoviePoster)
            .load(Constants.POSTER_BASE_URL + movie.posterPath)
            .into(binding.cvIvMoviePoster)

        binding.root.setOnClickListener {
            onClick.invoke(movie.id)
        }
    }

}