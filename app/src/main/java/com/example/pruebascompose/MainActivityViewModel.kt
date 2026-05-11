package com.example.pruebascompose

import androidx.lifecycle.ViewModel
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
    private val  getMoviesListUseCase: GetMoviesListUseCase,
    private  val getMoviesUpcomingListUseCase: GetMoviesUpcomingListUseCase,
    private  val getMoviesTopRatedUseCase: GetMoviesTopRatedUseCase,
    private  val getMoviesNowPlayingListUseCase: GetMoviesNowPlayingListUseCase
): BaseViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState

    fun getMovies(){
        executeUseCase(
            action = {
                val movie = getMoviesListUseCase.execute(Unit).results
                _mainState.value = _mainState.value.copy(
                    movies = movie
                )
            },
            exceptionHandler = {},
            finallyHandler = {}
        )
    }

    fun getMoviesTopRated(){
        executeUseCase(
            action = {
                val movie = getMoviesTopRatedUseCase.execute(Unit).results
                _mainState.value = _mainState.value.copy(
                    movies = movie
                )
            },
            exceptionHandler = {},
            finallyHandler = {}
        )
    }

    fun getMoviesUpcoming(){
        executeUseCase(
            action = {
                val movie = getMoviesUpcomingListUseCase.execute(Unit).results
                _mainState.value = _mainState.value.copy(
                    movies = movie
                )
            },
            exceptionHandler = {},
            finallyHandler = {}
        )
    }

    fun getMoviesNowRating(){
        executeUseCase(
            action = {
                val movie = getMoviesNowPlayingListUseCase.execute(Unit).results
                _mainState.value = _mainState.value.copy(
                    movies = movie
                )
            },
            exceptionHandler = {},
            finallyHandler = {}
        )
    }
}