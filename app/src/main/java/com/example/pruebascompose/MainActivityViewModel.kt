package com.example.pruebascompose

import androidx.lifecycle.ViewModel
import com.example.pruebascompose.core.BaseViewModel
import com.example.pruebascompose.domain.useCase.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val  getMoviesListUseCase: GetMoviesListUseCase
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
}