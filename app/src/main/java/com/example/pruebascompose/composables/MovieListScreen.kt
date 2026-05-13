package com.example.pruebascompose.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pruebascompose.MainActivityViewModel
import com.example.pruebascompose.domain.model.MovieBO

@Composable
fun MovieListScreen(
    innerPadding: PaddingValues = PaddingValues(),
    onMovieClick: (MovieBO) -> Unit
) {
    val viewModel: MainActivityViewModel = hiltViewModel()
    val state by viewModel.mainState.collectAsState()

    LaunchedEffect(Unit) { viewModel.getMovies() }

    Column(modifier = Modifier.padding(innerPadding)) {
        LazyColumn {
            items(state.movies.size) { index ->
                val movie = state.movies[index]
                MovieItemOverlay(movie = movie, onCLick = { onMovieClick(movie) })
            }
        }
    }
}

@Composable
fun MovieLisTopRatedtScreen(
    innerPadding: PaddingValues = PaddingValues(),
    onMovieClick: (MovieBO) -> Unit
) {
    val viewModel: MainActivityViewModel = hiltViewModel()
    val state by viewModel.mainState.collectAsState()

    LaunchedEffect(Unit) { viewModel.getMoviesTopRated() }

    Column(modifier = Modifier.padding(innerPadding)) {
        LazyColumn {
            items(state.movies.size) { index ->
                val movie = state.movies[index]
                MovieItemOverlay(movie = movie, onCLick = { onMovieClick(movie) })
            }
        }
    }
}

@Composable
fun MovieListUpcomingScreen(
    innerPadding: PaddingValues = PaddingValues(),
    onMovieClick: (MovieBO) -> Unit
) {
    val viewModel: MainActivityViewModel = hiltViewModel()
    val state by viewModel.mainState.collectAsState()

    LaunchedEffect(Unit) { viewModel.getMoviesUpcoming() }

    Column(modifier = Modifier.padding(innerPadding)) {
        LazyColumn {
            items(state.movies.size) { index ->
                val movie = state.movies[index]
                MovieItemOverlay(movie = movie, onCLick = { onMovieClick(movie) })
            }
        }
    }
}

@Composable
fun MovieListNowRatingScreen(
    innerPadding: PaddingValues = PaddingValues(),
    onMovieClick: (MovieBO) -> Unit
) {
    val viewModel: MainActivityViewModel = hiltViewModel()
    val state by viewModel.mainState.collectAsState()

    LaunchedEffect(Unit) { viewModel.getMoviesNowRating() }

    Column(modifier = Modifier.padding(innerPadding)) {
        LazyColumn {
            items(state.movies.size) { index ->
                val movie = state.movies[index]
                MovieItemOverlay(movie = movie, onCLick = { onMovieClick(movie) })
            }
        }
    }
}