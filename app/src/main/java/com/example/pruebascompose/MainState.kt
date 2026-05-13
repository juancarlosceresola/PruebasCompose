package com.example.pruebascompose

import com.example.pruebascompose.domain.model.MovieBO

data class MainState(
    val movies: List<MovieBO> = listOf()
)