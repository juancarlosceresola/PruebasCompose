package com.example.pruebascompose.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pruebascompose.composables.MovieListScreen
import com.example.pruebascompose.composables.MovieScreen
import com.example.pruebascompose.data.local.Movie
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

val MovieNavType = object : NavType<Movie>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Movie? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }
    override fun parseValue(value: String): Movie {
        return Json.decodeFromString(Uri.decode(value))
    }
    override fun serializeAsValue(value: Movie): String {
        return Uri.encode(Json.encodeToString(value))
    }
    override fun put(bundle: Bundle, key: String, value: Movie) {
        bundle.putString(key, Json.encodeToString(value))
    }
}

private val movieTypeMap = mapOf(typeOf<Movie>() to MovieNavType)

@Composable
fun MiAppNavegacion(innerPadding: PaddingValues = PaddingValues()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PantallaInicio
    ) {
        composable<PantallaInicio> {
            MovieListScreen(
                innerPadding = innerPadding,
                onMovieClick = { movie: Movie ->
                    navController.navigate(PantallaDetalle(movie = movie))
                }
            )
        }

        composable<PantallaDetalle>(typeMap = movieTypeMap) { backStackEntry ->
            val detalle = backStackEntry.toRoute<PantallaDetalle>()
            MovieScreen(movie = detalle.movie, onCLick = {})
        }
    }
}