package com.example.pruebascompose

import com.example.pruebascompose.data.local.Movie

data class MainState (
    val movies: List<Movie> = listOf()
)