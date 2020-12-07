package com.shannon.moviemvvm.ui.movies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shannon.moviemvvm.data.repository.NetworkState
import com.shannon.moviemvvm.data.model.Movie
import com.shannon.moviemvvm.databinding.MovieListItemBinding
import com.shannon.moviemvvm.databinding.NetworkStateItemBinding
import com.shannon.moviemvvm.ui.details.SingleMovieActivity
import com.shannon.moviemvvm.utils.visible

class PopularMoviePagedListAdapter(private val context: Context) :
    PagedListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MOVIE_VIEW_TYPE) {
            return MovieItemViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context)))
        } else {
            return NetworkStateItemViewHolder(NetworkStateItemBinding.inflate(LayoutInflater.from(parent.context)))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == MOVIE_VIEW_TYPE) {
            (holder as MovieItemViewHolder).bind(getItem(position), context)
        } else {
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }


    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }


    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }


    class MovieItemViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie?, context: Context) {
            binding.movie = movie

            itemView.setOnClickListener {
                val intent = Intent(context, SingleMovieActivity::class.java)
                intent.putExtra("id", movie?.id)
                context.startActivity(intent)
            }

        }

    }

    class NetworkStateItemViewHolder(private val binding: NetworkStateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(networkState: NetworkState?) {
            if (networkState != null) {
                val networkVisibility = networkState == NetworkState.LOADING
                binding.progressBarItem.visible(networkVisibility)

                val errorVisibility =
                    networkState == NetworkState.ERROR || networkState == NetworkState.ENDOFLIST
                binding.errorMsgItem.visible(errorVisibility)
                binding.errorMsgItem.text = networkState.msg
            }

        }
    }


    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }

    }


}