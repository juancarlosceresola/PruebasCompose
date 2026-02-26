package com.example.pruebascompose.navigation

import kotlinx.serialization.Serializable


@Serializable
object PantallaInicio

@Serializable
data class PantallaDetalle(val usuarioId: String) // Ejemplo con argumento