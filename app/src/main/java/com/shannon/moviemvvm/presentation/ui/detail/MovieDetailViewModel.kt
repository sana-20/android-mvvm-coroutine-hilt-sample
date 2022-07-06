package com.shannon.moviemvvm.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shannon.moviemvvm.domain.dto.MovieDetailDto
import com.shannon.moviemvvm.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val detailUseCase: GetMovieDetailUseCase) : ViewModel() {

    private val _items = MutableLiveData<MovieDetailDto>()
    val items: LiveData<MovieDetailDto> = _items

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val result = detailUseCase.invoke(movieId)
            _items.value = result
        }
    }

}