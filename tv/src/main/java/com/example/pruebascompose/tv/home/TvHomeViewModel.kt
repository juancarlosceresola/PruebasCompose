package com.example.pruebascompose.tv.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebascompose.domain.useCase.GetMoviesListUseCase
import com.example.pruebascompose.domain.useCase.GetMoviesNowPlayingListUseCase
import com.example.pruebascompose.domain.useCase.GetMoviesTopRatedUseCase
import com.example.pruebascompose.domain.useCase.GetMoviesUpcomingListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvHomeViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getMoviesTopRatedUseCase: GetMoviesTopRatedUseCase,
    private val getMoviesNowPlayingListUseCase: GetMoviesNowPlayingListUseCase,
    private val getMoviesUpcomingListUseCase: GetMoviesUpcomingListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(TvHomeState())
    val state: StateFlow<TvHomeState> = _state.asStateFlow()

    init {
        loadAllMovies()
    }

    private fun loadAllMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val popular = async {
                runCatching { getMoviesListUseCase.execute(Unit).movies }.getOrDefault(emptyList())
            }
            val topRated = async {
                runCatching { getMoviesTopRatedUseCase.execute(Unit).movies }.getOrDefault(emptyList())
            }
            val nowPlaying = async {
                runCatching { getMoviesNowPlayingListUseCase.execute(Unit).movies }.getOrDefault(emptyList())
            }
            val upcoming = async {
                runCatching { getMoviesUpcomingListUseCase.execute(Unit).movies }.getOrDefault(emptyList())
            }

            _state.update {
                it.copy(
                    popularMovies = popular.await(),
                    topRatedMovies = topRated.await(),
                    nowPlayingMovies = nowPlaying.await(),
                    upcomingMovies = upcoming.await(),
                    isLoading = false
                )
            }
        }
    }
}
