package com.example.pruebascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.pruebascompose.data.remotedata.Network
import com.example.pruebascompose.ui.theme.PruebasComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniciar()
        enableEdgeToEdge()
        setContent {
            PruebasComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun iniciar() {
        lifecycleScope.launch {
            Network.apiService.getPeliculas(getString(R.string.app_key), "es-ES")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
     var f = name
    TextField( value = name ,
        label = {"mi label"},
        modifier = modifier,
        onValueChange = { f = it}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PruebasComposeTheme {
        Greeting("Android")
    }
}