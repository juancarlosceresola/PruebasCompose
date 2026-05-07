package com.example.pruebascompose.navigation

import com.example.pruebascompose.data.local.Movie
import kotlinx.serialization.Serializable


@Serializable
object PantallaInicio

@Serializable
data class PantallaDetalle(val movie: Movie)