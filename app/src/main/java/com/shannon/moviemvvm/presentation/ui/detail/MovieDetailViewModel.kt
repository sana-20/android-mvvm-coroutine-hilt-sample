package com.shannon.moviemvvm.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shannon.moviemvvm.domain.usecase.GetMovieDetailUseCase
import com.shannon.moviemvvm.presentation.mapper.DetailMapper
import com.shannon.moviemvvm.presentation.model.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailUseCase: GetMovieDetailUseCase,
    private val detailMapper: DetailMapper
    ) : ViewModel() {

    private val _items = MutableLiveData<Detail>()
    val items: LiveData<Detail> = _items

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val result = detailUseCase.invoke(movieId)
            _items.value = detailMapper.mapToDetail(result)
        }
    }

}