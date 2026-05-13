package com.example.pruebascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pruebascompose.navigation.MiAppNavegacion
import com.example.pruebascompose.ui.theme.PruebasComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebasComposeTheme {
                MiAppNavegacion()
            }
        }
    }
}
