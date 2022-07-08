package com.shannon.moviemvvm.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.MoviesEntity
import com.example.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovieUseCase
    ) : ViewModel() {

    fun getMovies(): Flow<PagingData<MoviesEntity>> {
        return getMoviesUseCase.invoke().cachedIn(viewModelScope)
    }

}