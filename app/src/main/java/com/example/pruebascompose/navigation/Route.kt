package com.example.pruebascompose.navigation

import com.example.pruebascompose.data.local.Movie
import kotlinx.serialization.Serializable

@Serializable object PantallaInicio

@Serializable data class PantallaDetalle(val movie: Movie)

@Serializable data class PantallaDetallev2(val movie: Movie)
@Serializable data class PantallaDetallev3(val movie: Movie)

@Serializable object PantallaGeneral
@Serializable object PantallaPopulares
@Serializable object PantallaUltimas
@Serializable object PantallaTopRated