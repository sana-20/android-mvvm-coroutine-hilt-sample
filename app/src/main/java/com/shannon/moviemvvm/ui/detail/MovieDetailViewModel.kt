package com.shannon.moviemvvm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shannon.moviemvvm.data.MovieDetails
import com.shannon.moviemvvm.domain.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val detailUseCase: GetMovieDetailUseCase) : ViewModel() {

    private val _items = MutableLiveData<MovieDetails>()
    val items: LiveData<MovieDetails> = _items

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val result = detailUseCase.invoke(movieId)
            _items.value = result
        }
    }

}