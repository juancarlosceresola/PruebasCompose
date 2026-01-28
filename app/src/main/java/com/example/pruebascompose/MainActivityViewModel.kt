package com.example.pruebascompose

import androidx.lifecycle.ViewModel
import com.example.pruebascompose.core.BaseViewModel
import com.example.pruebascompose.domain.useCase.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val  getMoviesListUseCase: GetMoviesListUseCase
): BaseViewModel() {

    fun getMovies(){
        executeUseCase(
            action = {},
            exceptionHandler = {},
            finallyHandler = {}
        )
    }
}