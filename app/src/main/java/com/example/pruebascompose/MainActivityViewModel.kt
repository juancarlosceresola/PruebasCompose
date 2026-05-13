package com.example.pruebascompose

import com.example.pruebascompose.core.BaseViewModel
import com.example.pruebascompose.domain.useCase.GetMoviesListUseCase
import com.example.pruebascompose.domain.useCase.GetMoviesNowPlayingListUseCase
import com.example.pruebascompose.domain.useCase.GetMoviesTopRatedUseCase
import com.example.pruebascompose.domain.useCase.GetMoviesUpcomingListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getMoviesUpcomingListUseCase: GetMoviesUpcomingListUseCase,
    private val getMoviesTopRatedUseCase: GetMoviesTopRatedUseCase,
    private val getMoviesNowPlayingListUseCase: GetMoviesNowPlayingListUseCase
) : BaseViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState

    fun getMovies() {
        executeUseCase(
            action = {
                val movies = getMoviesListUseCase.execute(Unit).movies
                _mainState.value = _mainState.value.copy(movies = movies)
            },
            exceptionHandler = {},
            finallyHandler = {}
        )
    }

    fun getMoviesTopRated() {
        executeUseCase(
            action = {
                val movies = getMoviesTopRatedUseCase.execute(Unit).movies
                _mainState.value = _mainState.value.copy(movies = movies)
            },
            exceptionHandler = {},
            finallyHandler = {}
        )
    }

    fun getMoviesUpcoming() {
        executeUseCase(
            action = {
                val movies = getMoviesUpcomingListUseCase.execute(Unit).movies
                _mainState.value = _mainState.value.copy(movies = movies)
            },
            exceptionHandler = {},
            finallyHandler = {}
        )
    }

    fun getMoviesNowRating() {
        executeUseCase(
            action = {
                val movies = getMoviesNowPlayingListUseCase.execute(Unit).movies
                _mainState.value = _mainState.value.copy(movies = movies)
            },
            exceptionHandler = {},
            finallyHandler = {}
        )
    }
}