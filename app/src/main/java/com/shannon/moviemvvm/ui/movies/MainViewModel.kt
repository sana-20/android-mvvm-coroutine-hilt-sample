package com.shannon.moviemvvm.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shannon.moviemvvm.data.Movie
import com.shannon.moviemvvm.domain.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMoviesUseCase: GetMovieUseCase) : ViewModel() {

    fun getMovies(): Flow<PagingData<Movie>> {
        return getMoviesUseCase.invoke().cachedIn(viewModelScope)
    }

}