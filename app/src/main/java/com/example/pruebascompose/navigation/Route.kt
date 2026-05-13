package com.example.pruebascompose.navigation

import com.example.pruebascompose.domain.model.MovieBO
import kotlinx.serialization.Serializable

@Serializable object PantallaInicio

@Serializable data class PantallaDetalle(val movie: MovieBO)
@Serializable data class PantallaDetallev2(val movie: MovieBO)
@Serializable data class PantallaDetallev3(val movie: MovieBO)

@Serializable object PantallaGeneral
@Serializable object PantallaPopulares
@Serializable object PantallaUltimas
@Serializable object PantallaTopRated