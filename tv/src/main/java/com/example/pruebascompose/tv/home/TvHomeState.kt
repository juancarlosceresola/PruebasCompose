package com.example.pruebascompose.tv.home

import com.example.pruebascompose.domain.model.MovieBO

data class TvHomeState(
    val popularMovies: List<MovieBO> = emptyList(),
    val topRatedMovies: List<MovieBO> = emptyList(),
    val nowPlayingMovies: List<MovieBO> = emptyList(),
    val upcomingMovies: List<MovieBO> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
