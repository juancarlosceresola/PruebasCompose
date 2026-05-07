package com.example.pruebascompose.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pruebascompose.data.local.Movie

@Composable
fun MovieScreen(movie: Movie, onCLick: (String) -> Unit ) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
    }
}
