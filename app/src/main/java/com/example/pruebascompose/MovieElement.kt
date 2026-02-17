package com.example.pruebascompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pruebascompose.data.local.Movie
import com.example.pruebascompose.domain.bo.BasicMovieBO
import com.example.pruebascompose.domain.bo.MovieBO

@Composable
fun Movie(movie: Movie,){
    Row {
    AsyncImage(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .width(250.dp)
            .height(350.dp)
            .clickable {

            },
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.poster_path)
            .crossfade(false)
            .build(),
        contentDescription = "Live image picture",
        contentScale = ContentScale.FillBounds

    )}
}
