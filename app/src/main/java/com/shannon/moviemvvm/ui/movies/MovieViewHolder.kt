package com.shannon.moviemvvm.ui.movies

import androidx.recyclerview.widget.RecyclerView
import com.shannon.moviemvvm.data.Movie
import com.shannon.moviemvvm.databinding.MovieListItemBinding

class MovieViewHolder(private val binding: MovieListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, onClick: (movieId: Int) -> Unit) {
        binding.movie = movie
        binding.root.setOnClickListener {
            onClick.invoke(movie.id)
        }
    }

}