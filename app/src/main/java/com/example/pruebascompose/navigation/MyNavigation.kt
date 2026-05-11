package com.example.pruebascompose.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movies.ui.detail.MovieDetailScreen
import com.example.movies.ui.detail.MovieDetailScreenCinematic
import com.example.movies.ui.detail.MovieDetailScreenEditorial
import com.example.pruebascompose.composables.MovieLisTopRatedtScreen
import com.example.pruebascompose.composables.MovieListNowRatingScreen
import com.example.pruebascompose.composables.MovieListScreen
import com.example.pruebascompose.composables.MovieListUpcomingScreen
import com.example.pruebascompose.composables.MovieScreen
import com.example.pruebascompose.data.local.Movie
import kotlin.reflect.typeOf

private val movieTypeMap = mapOf(typeOf<Movie>() to MovieNavType)

@Composable
fun MiAppNavegacion() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PantallaGeneral,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<PantallaGeneral> {
                MovieListScreen(
                    onMovieClick = { movie: Movie ->
                        navController.navigate(PantallaDetalle(movie = movie))
                    }
                )
            }
            composable<PantallaPopulares> {
                MovieListNowRatingScreen(
                    onMovieClick = { movie: Movie ->
                        navController.navigate(PantallaDetalle(movie = movie))
                    }
                )
            }
            composable<PantallaUltimas> {
                MovieListUpcomingScreen(
                    onMovieClick = { movie: Movie ->
                        navController.navigate(PantallaDetallev2(movie = movie))
                    }
                )
            }
            composable<PantallaTopRated> {
                MovieLisTopRatedtScreen(
                    onMovieClick = { movie: Movie ->
                        navController.navigate(PantallaDetallev3(movie = movie))
                    }
                )
            }
            composable<PantallaDetalle>(typeMap = movieTypeMap) { backStackEntry ->
                val detalle = backStackEntry.toRoute<PantallaDetalle>()
                MovieDetailScreen(movie = detalle.movie, onBack = { navController.popBackStack() })
            }
            composable<PantallaDetallev2>(typeMap = movieTypeMap) { backStackEntry ->
                val detalle = backStackEntry.toRoute<PantallaDetalle>()
                MovieDetailScreenCinematic(movie = detalle.movie, onBack = { navController.popBackStack() })
            }
            composable<PantallaDetallev3>(typeMap = movieTypeMap) { backStackEntry ->
                val detalle = backStackEntry.toRoute<PantallaDetalle>()
                MovieDetailScreenEditorial(movie = detalle.movie, onBack = { navController.popBackStack() })
            }
        }
    }
}