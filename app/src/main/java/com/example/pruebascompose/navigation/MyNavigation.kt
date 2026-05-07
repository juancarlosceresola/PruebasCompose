package com.example.pruebascompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pruebascompose.composables.MovieListScreen
import com.example.pruebascompose.composables.MovieScreen
import com.example.pruebascompose.data.local.Movie

@Composable
fun MiAppNavegacion() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PantallaInicio
    ) {
        composable<PantallaInicio> {
            MovieListScreen(
                onMovieClick = { movie: Movie ->
                    navController.navigate(PantallaDetalle(movie = movie))
                }
            )
        }

        composable<PantallaDetalle> { backStackEntry ->
            val detalle = backStackEntry.toRoute<PantallaDetalle>()
            MovieScreen(movie = detalle.movie, onCLick = {})
        }
    }
}