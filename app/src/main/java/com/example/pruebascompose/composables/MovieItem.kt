package com.example.pruebascompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pruebascompose.data.local.Movie


@Composable
fun MovieItemEditorial(
    movie: Movie,
    modifier: Modifier = Modifier,
    onCLick: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = movie.poster_path,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.Black)
                .clickable { onCLick(movie.title) },
        )
        Spacer(Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                ),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(12.dp))
            RatingLabel(movie.vote_average.toFloat())
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = movie.release_date,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}


@Composable
fun MovieItemRow(
    movie: Movie,
    modifier: Modifier = Modifier,
    onCLick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        AsyncImage(
            model = movie.poster_path,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(84.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .clickable { onCLick(movie.title) },
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = movie.release_date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(8.dp))
            RatingLabel(movie.vote_average.toFloat())
        }
    }
}


@Composable
fun MovieItemOverlay(
    movie: Movie,
    modifier: Modifier = Modifier,
    onCLick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.Black),
    ) {
        AsyncImage(
            model = movie.poster_path,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onCLick(movie.title) },
        )
        // Degradado para legibilidad del texto
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.40f to Color.Transparent,
                        0.70f to Color.Black.copy(alpha = 0.55f),
                        1.00f to Color.Black.copy(alpha = 0.92f),
                    ),
                ),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(18.dp),
        ) {
            Text(
                text = movie.release_date,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.85f),
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = movie.title,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                lineHeight = 24.sp,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = movie.overview,
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}


@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun MovieItemEditorialPreview() {
    com.example.pruebascompose.ui.theme.PruebasComposeTheme {
        MovieItemEditorial(
            movie = previewMovie(),
            onCLick = {}
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun MovieItemRowPreview() {
    com.example.pruebascompose.ui.theme.PruebasComposeTheme {
        MovieItemRow(
            movie = previewMovie(),
            onCLick = {}
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun MovieItemOverlayPreview() {
    com.example.pruebascompose.ui.theme.PruebasComposeTheme {
        MovieItemOverlay(
            movie = previewMovie(),
            onCLick = {}
        )
    }
}

private fun previewMovie() = Movie(
    adult = false,
    backdrop_path = "",
    id = 1,
    original_language = "en",
    original_title = "Super Mario Bros",
    overview = "Mario y Luigi emprenden una aventura épica para salvar el Reino Champiñón.",
    popularity = 9.5,
    poster_path = "android.resource://com.example.pruebascompose/drawable/mario",
    release_date = "2023-04-05",
    title = "Super Mario Bros",
    video = false,
    vote_average = 7.5,
    vote_count = 8420
)

@Composable
private fun RatingLabel(rating: Float) {
    Text(
        text = "★ ${"%.1f".format(rating)}",
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
        color = MaterialTheme.colorScheme.onSurface,
    )
}


