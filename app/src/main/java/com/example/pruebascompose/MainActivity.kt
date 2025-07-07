package com.example.pruebascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pruebascompose.core.extensions.parseResponse
import com.example.pruebascompose.data.local.Movie
import com.example.pruebascompose.data.local.PagingResult
import com.example.pruebascompose.data.mappers.toPagingResult
import com.example.pruebascompose.data.remotedata.Network
import com.example.pruebascompose.ui.theme.PruebasComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            PruebasComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    iniciar(
                        modifier = Modifier.padding(innerPadding),
                    )
                    }
                }

            }
    }

    @Composable
    private fun iniciar( modifier: Modifier = Modifier) {
        var launch by remember { mutableStateOf(false) }
        var pagingResult by remember { mutableStateOf<List<Movie>>(listOf()) }
        var mostrar by remember { mutableStateOf("") }

        LaunchedEffect(key1 = Unit) {
            val l = Network.apiService.getPeliculas().parseResponse().toPagingResult()
            pagingResult = l.results
        }

        LaunchedEffect(mostrar) {
            MySnackBar(mostrar)
        }

         Column {
             LazyColumn {
                 items(pagingResult.size) { movie->
                     ElementMovie(pagingResult[movie]){ title->
                        mostrar = title
                     }
                 }
             }
         }
    }


    @Composable
    fun ElementMovie(movie: Movie, onCLick: (String) -> Unit ) {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                        .padding(20.dp)
                        .clickable {
                            onCLick(movie.title)
                        },
                    alignment = Alignment.Center,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.poster_path)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Live image picture",
                    contentScale = ContentScale.Fit,
                )


            Text(modifier = Modifier.padding(start = 8.dp,
                end = 8.dp,top = 8.dp,bottom = 18.dp)

                .fillMaxWidth(),
                text = movie.title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
                 )

            Text(modifier = Modifier.padding(16.dp)
                .fillMaxWidth(),
                text = movie.title,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }


    suspend fun MySnackBar(title:String) {
        val snackbarHostState = SnackbarHostState()
            snackbarHostState.showSnackbar(message = title)

    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        var f = name
        TextField(value = name,
            label = { "mi label" },
            modifier = modifier,
            onValueChange = { f = it }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        PruebasComposeTheme {
            Greeting("Android")
        }
    }
}