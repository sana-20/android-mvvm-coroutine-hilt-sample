package com.shannon.moviemvvm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetMovieDetailUseCase
import com.shannon.moviemvvm.mapper.DetailMapper
import com.shannon.moviemvvm.state.DetailViewState
import com.shannon.moviemvvm.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailUseCase: GetMovieDetailUseCase,
    private val detailMapper: DetailMapper
) : ViewModel() {

    private val _detailViewState = MutableLiveData<DetailViewState>()
    val detailViewState: LiveData<DetailViewState> = _detailViewState

    private val _toastEvent = MutableLiveData<Event<String>>()
    val toastEvent: LiveData<Event<String>> = _toastEvent

    private val _loadingEvent = MutableLiveData<Event<Boolean>>()
    val loadingEvent: LiveData<Event<Boolean>> = _loadingEvent

    fun getMovieDetail(movieId: Int) {
        _loadingEvent.value = Event(true)
        viewModelScope.launch {
            val result = detailUseCase.invoke(movieId)
            val detail = detailMapper.mapToDetail(result)
            _detailViewState.value = DetailViewState.Data(detail)
            _loadingEvent.value = Event(false)
        }
    }

    fun onClickImage() {
        _toastEvent.value = Event("Image Clicked")
    }

}