package com.example.pruebascompose.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pruebascompose.MainActivityViewModel
import com.example.pruebascompose.data.local.Movie

@Composable
fun MovieListScreen(onMovieClick: (Movie) -> Unit) {
    val viewModel: MainActivityViewModel = hiltViewModel()
    val state by viewModel.mainState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }

    Column {
        LazyColumn {
            items(state.movies.size) { index ->
                val movie = state.movies[index]
                MovieScreen(movie = movie, onCLick = { onMovieClick(movie) })
            }
        }
    }
}