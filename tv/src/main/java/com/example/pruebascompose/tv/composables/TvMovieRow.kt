package com.example.pruebascompose.tv.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.example.pruebascompose.domain.model.MovieBO

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvMovieRow(
    title: String,
    movies: List<MovieBO>,
    modifier: Modifier = Modifier
) {
    if (movies.isEmpty()) return

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 48.dp, bottom = 14.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) { movie ->
                TvMovieCard(
                    movie = movie,
                    onClick = { }
                )
            }
        }
    }
}
