package com.shannon.moviemvvm.presentation.state

import com.shannon.moviemvvm.presentation.model.Detail

sealed class DetailViewState {
    object Loading: DetailViewState()
    data class Data(val data: Detail) : DetailViewState()
    object Error: DetailViewState()
}