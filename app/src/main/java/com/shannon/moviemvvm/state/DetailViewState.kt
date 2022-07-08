package com.shannon.moviemvvm.state

import com.shannon.moviemvvm.model.Detail

sealed class DetailViewState {
    object Loading: DetailViewState()
    data class Data(val data: Detail) : DetailViewState()
    object Error: DetailViewState()
}