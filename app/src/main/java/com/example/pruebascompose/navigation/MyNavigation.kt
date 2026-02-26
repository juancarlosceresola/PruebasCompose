package com.example.pruebascompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pruebascompose.MainActivity

@Composable
fun MiAppNavegacion() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PantallaInicio.toString() // Pantalla de salida
    ) {
        // Definimos la pantalla de Inicio
        composable<PantallaInicio> {
            iniciar(
                onIrADetalle = { id ->
                    navController.navigate(PantallaDetalle(usuarioId = id))
                }
            )
        }

        // Definimos la pantalla de Detalle
        composable<PantallaDetalle> { backStackEntry ->
            // Obtenemos los argumentos automáticamente
            val detalle = backStackEntry <PantallaDetalle>()
            DetalleScreen(id = detalle.usuarioId)
        }
    }
}