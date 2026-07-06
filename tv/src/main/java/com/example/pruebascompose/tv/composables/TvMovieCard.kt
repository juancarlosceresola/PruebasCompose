package com.example.pruebascompose.tv.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.example.pruebascompose.domain.model.MovieBO

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvMovieCard(
    movie: MovieBO,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(140.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f),
            shape = CardDefaults.shape(shape = RoundedCornerShape(10.dp)),
            scale = CardDefaults.scale(focusedScale = 1.12f),
            border = CardDefaults.border(
                focusedBorder = Border(
                    border = BorderStroke(
                        width = 3.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
            ),
            colors = CardDefaults.colors(
                containerColor = Color(0xFF1A1625),
                focusedContainerColor = Color(0xFF1A1625)
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = movie.posterPath,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Bottom gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.38f)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xEB0E0B14))
                            )
                        )
                )
                // Rating badge
                Text(
                    text = "★ ${"%.1f".format(movie.voteAverage)}",
                    color = Color(0xFFF5A524),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 7.dp, bottom = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = movie.title,
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 11.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
