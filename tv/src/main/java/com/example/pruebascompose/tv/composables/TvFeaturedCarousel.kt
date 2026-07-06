package com.example.pruebascompose.tv.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import androidx.tv.material3.rememberCarouselState
import coil.compose.AsyncImage
import com.example.pruebascompose.domain.model.MovieBO

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvFeaturedCarousel(
    movies: List<MovieBO>,
    modifier: Modifier = Modifier
) {
    if (movies.isEmpty()) return
    val slides = movies.take(5)
    val carouselState = rememberCarouselState()

    Carousel(
        itemCount = slides.size,
        modifier = modifier
            .fillMaxWidth()
            .height(390.dp),
        carouselState = carouselState,
        autoScrollDurationMillis = 6_000L,
        carouselIndicator = {
            CarouselDefaults.IndicatorRow(
                itemCount = slides.size,
                activeItemIndex = carouselState.activeItemIndex,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 48.dp, bottom = 20.dp)
            )
        }
    ) { index ->
        val movie = slides[index]
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.backdropPath.ifEmpty { movie.posterPath },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            0f to Color(0xFF0E0B14).copy(alpha = 0.90f),
                            0.42f to Color(0xFF0E0B14).copy(alpha = 0.30f),
                            1f to Color.Transparent
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.28f)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFF0E0B14))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(0.50f)
                    .padding(start = 48.dp, bottom = 44.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 40.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "★ ${"%.1f".format(movie.voteAverage)}",
                        color = Color(0xFFF5A524),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    val year = movie.releaseDate.takeIf { it.length >= 4 }?.take(4)
                    if (year != null) {
                        Text(
                            text = year,
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 13.sp
                        )
                    }
                    if (movie.adult) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(text = "+18", color = Color.White, fontSize = 10.sp)
                        }
                    }
                }
                Text(
                    text = movie.overview,
                    color = Color.White.copy(alpha = 0.68f),
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            focusedContainerColor = Color.White,
                            focusedContentColor = Color(0xFF0E0B14)
                        )
                    ) {
                        Text(text = "▶  Reproducir", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                    OutlinedButton(onClick = { }) {
                        Text(text = "ℹ  Más info", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}
