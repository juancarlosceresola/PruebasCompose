package com.example.pruebascompose.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pruebascompose.tv.home.TvHomeScreen
import com.example.pruebascompose.tv.theme.TvTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvTheme {
                TvHomeScreen()
            }
        }
    }
}
