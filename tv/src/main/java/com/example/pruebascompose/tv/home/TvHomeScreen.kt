package com.example.pruebascompose.tv.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.pruebascompose.tv.composables.TvFeaturedCarousel
import com.example.pruebascompose.tv.composables.TvMovieRow

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvHomeScreen(
    viewModel: TvHomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E0B14))
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color(0xFF5B3FE4)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(28.dp),
                contentPadding = PaddingValues(bottom = 48.dp)
            ) {
                item {
                    TvFeaturedCarousel(movies = state.popularMovies)
                }
                item {
                    TvMovieRow(
                        title = "Populares",
                        movies = state.popularMovies
                    )
                }
                item {
                    TvMovieRow(
                        title = "Mejor Valoradas",
                        movies = state.topRatedMovies
                    )
                }
                item {
                    TvMovieRow(
                        title = "En Cartelera",
                        movies = state.nowPlayingMovies
                    )
                }
                item {
                    TvMovieRow(
                        title = "Próximas",
                        movies = state.upcomingMovies
                    )
                }
            }
        }
    }
}
