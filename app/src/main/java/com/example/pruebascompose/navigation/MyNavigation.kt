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
import com.example.pruebascompose.composables.MovieListScreen
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
                Text("Populares")
            }
            composable<PantallaUltimas> {
                Text("Últimas")
            }
            composable<PantallaTopRated> {
                Text("Top Rated")
            }
            composable<PantallaDetalle>(typeMap = movieTypeMap) { backStackEntry ->
                val detalle = backStackEntry.toRoute<PantallaDetalle>()
                MovieScreen(movie = detalle.movie, onCLick = {})
            }
        }
    }
}