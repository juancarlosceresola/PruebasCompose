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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.example.pruebascompose.R
import com.example.pruebascompose.domain.model.MovieBO
import com.example.pruebascompose.ui.theme.Dimens
import com.example.pruebascompose.ui.theme.OverlayBlack92
import com.example.pruebascompose.ui.theme.OverlayBlack55
import com.example.pruebascompose.ui.theme.OverlayWhite85


@Composable
fun MovieItemEditorial(
    movie: MovieBO,
    modifier: Modifier = Modifier,
    onCLick: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(Dimens.CornerCard))
                .background(Color.Black)
                .clickable { onCLick(movie.title) },
        )
        Spacer(Modifier.height(Dimens.SpacingMediumLarge))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = Dimens.LineHeightTitle,
                ),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(Dimens.SpacingMedium))
            RatingLabel(movie.voteAverage.toFloat())
        }
        Spacer(Modifier.height(Dimens.SpacingXSmall))
        Text(
            text = movie.releaseDate,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(Dimens.SpacingSmall))
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = Dimens.LineHeightBodyMed),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}


@Composable
fun MovieItemRow(
    movie: MovieBO,
    modifier: Modifier = Modifier,
    onCLick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.SpacingMediumLarge),
        horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMediumLarge),
    ) {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(Dimens.PosterThumbnailWidth)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(Dimens.CornerSmall))
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
            Spacer(Modifier.height(Dimens.SpacingXXSmall))
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(Dimens.SpacingSmall))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = Dimens.LineHeightBody),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(Dimens.SpacingSmall))
            RatingLabel(movie.voteAverage.toFloat())
        }
    }
}


@Composable
fun MovieItemOverlay(
    movie: MovieBO,
    modifier: Modifier = Modifier,
    onCLick: (MovieBO) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = Dimens.SpacingXXLarge, end = Dimens.SpacingXXLarge, bottom = Dimens.SpacingXXLarge)
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(Dimens.CornerOverlay))
            .background(Color.Black),
    ) {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onCLick(movie) },
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.40f to Color.Transparent,
                        0.70f to OverlayBlack55,
                        1.00f to OverlayBlack92,
                    ),
                ),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(Dimens.SpacingXLarge),
        ) {
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.labelSmall,
                color = OverlayWhite85,
            )
            Spacer(Modifier.height(Dimens.SpacingXSmall))
            Text(
                text = movie.title,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = Dimens.TextOverlayTitle,
                lineHeight = Dimens.LineHeightNormal,
            )
            Spacer(Modifier.height(Dimens.SpacingSmall))
            Text(
                text = movie.overview,
                color = OverlayWhite85,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = Dimens.LineHeightBody),
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
        MovieItemEditorial(movie = previewMovie(), onCLick = {})
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun MovieItemRowPreview() {
    com.example.pruebascompose.ui.theme.PruebasComposeTheme {
        MovieItemRow(movie = previewMovie(), onCLick = {})
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun MovieItemOverlayPreview() {
    com.example.pruebascompose.ui.theme.PruebasComposeTheme {
        MovieItemOverlay(movie = previewMovie(), onCLick = {})
    }
}

private fun previewMovie() = MovieBO(
    adult = false, backdropPath = "", id = 1, originalLanguage = "en",
    originalTitle = "Super Mario Bros",
    overview = "Mario y Luigi emprenden una aventura épica para salvar el Reino Champiñón.",
    popularity = 9.5, posterPath = "", releaseDate = "2023-04-05",
    title = "Super Mario Bros", video = false, voteAverage = 7.5, voteCount = 8420
)

@Composable
private fun RatingLabel(rating: Float) {
    Text(
        text = stringResource(R.string.rating_format, rating),
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
        color = MaterialTheme.colorScheme.onSurface,
    )
}
