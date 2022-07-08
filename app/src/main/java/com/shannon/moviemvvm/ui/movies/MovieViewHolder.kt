package com.shannon.moviemvvm.ui.movies

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shannon.moviemvvm.databinding.MovieListItemBinding
import com.example.domain.entity.MoviesEntity

class MovieViewHolder(private val binding: MovieListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MoviesEntity, onClick: (movieId: Int) -> Unit) {
        binding.cvMovieTitle.text = movie.title
        binding.cvMovieReleaseDate.text = movie.releaseDate

        Glide.with(binding.cvIvMoviePoster)
            .load(movie.imageUrl)
            .into(binding.cvIvMoviePoster)

        binding.root.setOnClickListener {
            onClick.invoke(movie.id)
        }
    }

}