package com.example.movies.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.example.pruebascompose.R
import com.example.pruebascompose.domain.model.MovieBO
import com.example.pruebascompose.ui.theme.Dimens
import com.example.pruebascompose.ui.theme.OverlayBlack35
import com.example.pruebascompose.ui.theme.OverlayBlack40
import com.example.pruebascompose.ui.theme.OverlayWhite70
import com.example.pruebascompose.ui.theme.OverlayWhite90
import com.example.pruebascompose.ui.theme.StarYellow
import java.util.Locale

private fun fmtYear(date: String) = date.take(4)
private fun fmtCount(n: Int): String =
    if (n >= 1000) "%.1f".format(n / 1000.0).removeSuffix(".0") + "k" else n.toString()
private fun fmtPopularity(p: Double) = "%,d".format(p.toInt())
private fun fmtDate(date: String, months: Array<String>): String {
    val parts = date.split("-")
    if (parts.size != 3) return date
    val d = parts[2].toIntOrNull() ?: return date
    val m = parts[1].toIntOrNull()?.minus(1)?.let { months.getOrNull(it) } ?: return date
    return "$d $m ${parts[0]}"
}

@Composable
private fun langLabel(code: String): String = when (code) {
    "en" -> stringResource(R.string.lang_english)
    "es" -> stringResource(R.string.lang_spanish)
    "fr" -> stringResource(R.string.lang_french)
    "ja" -> stringResource(R.string.lang_japanese)
    "ko" -> stringResource(R.string.lang_korean)
    else -> code.uppercase(Locale.ROOT)
}

@Composable
private fun ageLabel(adult: Boolean): String =
    if (adult) stringResource(R.string.classification_adults_short)
    else stringResource(R.string.classification_all_short)


@Composable
fun MovieDetailScreen(
    movie: MovieBO,
    onBack: () -> Unit = {},
    onPlayTrailer: () -> Unit = {},
    onAddToList: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val scroll = rememberScrollState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scroll),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            BackdropWithPoster(movie)
            Spacer(Modifier.height(Dimens.SpacingLarge))
            RatingRow(movie)
            Spacer(Modifier.height(Dimens.SpacingLarge))
            ActionButtons(onPlayTrailer, onAddToList)
            SectionHeader(stringResource(R.string.label_synopsis))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = Dimens.LineHeightNormal),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = Dimens.SpacingXLarge),
            )
            SectionHeader(stringResource(R.string.label_details))
            DetailsGrid(movie)
            Spacer(Modifier.height(Dimens.SpacingBottom))
        }
        TopActions(
            onBack = onBack,
            modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth(),
        )
    }
}


@Composable
private fun BackdropWithPoster(movie: MovieBO) {
    val lang = langLabel(movie.originalLanguage)
    val age = ageLabel(movie.adult)
    val backdropUrl = "https://image.tmdb.org/t/p/w780${movie.backdropPath}"

    Box {
        Box(modifier = Modifier.fillMaxWidth().aspectRatio(16f / 10f)) {
            AsyncImage(
                model = backdropUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().background(Color.Black),
                colorFilter = ColorFilter.tint(OverlayBlack35, androidx.compose.ui.graphics.BlendMode.Darken),
            )
            Box(
                modifier = Modifier.fillMaxSize().background(
                    Brush.verticalGradient(
                        0.0f to Color.Transparent,
                        0.6f to Color.Transparent,
                        1.0f to MaterialTheme.colorScheme.background,
                    ),
                ),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.SpacingXLarge)
                .offset(y = Dimens.SpacingXXLarge),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMediumLarge),
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(Dimens.DetailPosterWidth)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(Dimens.CornerMedium))
                    .background(Color.Black),
            )
            Column(modifier = Modifier.weight(1f).padding(bottom = Dimens.SpacingXSmall)) {
                Text(
                    text = "${fmtYear(movie.releaseDate)} · $lang · $age",
                    color = OverlayWhite90,
                    fontSize = Dimens.TextSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(Dimens.SpacingXXSmall))
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = Dimens.TextPosterTitle,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = Dimens.LineHeightNormal,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                if (movie.originalTitle != movie.title) {
                    Text(
                        text = movie.originalTitle,
                        color = OverlayWhite70,
                        fontSize = Dimens.TextMediumSmall,
                        fontStyle = FontStyle.Italic,
                    )
                }
            }
        }
    }
}


@Composable
private fun TopActions(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = Dimens.SpacingSmall, vertical = Dimens.SpacingSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconCircleButton(onClick = onBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.action_back), tint = Color.White)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingXXSmall)) {
            IconCircleButton({}) {
                Icon(Icons.Filled.FavoriteBorder, contentDescription = stringResource(R.string.action_favorite), tint = Color.White)
            }
            IconCircleButton({}) {
                Icon(Icons.Filled.Share, contentDescription = stringResource(R.string.action_share), tint = Color.White)
            }
        }
    }
}

@Composable
private fun IconCircleButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(Dimens.IconButtonSize)
            .clip(CircleShape)
            .background(OverlayBlack40)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) { content() }
}


@Composable
private fun RatingRow(movie: MovieBO) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.SpacingXLarge),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Star, contentDescription = null, tint = StarYellow, modifier = Modifier.size(Dimens.SpacingSection))
            Spacer(Modifier.width(Dimens.SpacingXSmall))
            Column {
                Text(
                    text = "%.1f".format(movie.voteAverage),
                    fontWeight = FontWeight.Bold, fontSize = Dimens.TextScore,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = stringResource(R.string.votes_format, fmtCount(movie.voteCount)),
                    fontSize = Dimens.TextSmall, color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = stringResource(R.string.label_popularity),
                fontSize = Dimens.TextXXSmall, fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stringResource(R.string.popularity_format, fmtPopularity(movie.popularity)),
                fontSize = Dimens.TextBody, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}


@Composable
private fun ActionButtons(onPlayTrailer: () -> Unit, onAddToList: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.SpacingXLarge),
        horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall),
    ) {
        Button(
            onClick = onPlayTrailer,
            modifier = Modifier.weight(1f).height(Dimens.ButtonHeight),
            shape = RoundedCornerShape(Dimens.CornerMedium),
        ) {
            Icon(Icons.Filled.PlayArrow, contentDescription = null)
            Spacer(Modifier.width(Dimens.SpacingXSmall))
            Text(stringResource(R.string.action_watch_trailer), fontWeight = FontWeight.Bold)
        }
        OutlinedButton(
            onClick = onAddToList,
            modifier = Modifier.height(Dimens.ButtonHeight),
            shape = RoundedCornerShape(Dimens.CornerMedium),
        ) {
            Text(stringResource(R.string.action_add_to_list), fontWeight = FontWeight.SemiBold)
        }
    }
}


@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text.uppercase(Locale.ROOT),
        fontSize = Dimens.TextSmall, fontWeight = FontWeight.Bold,
        letterSpacing = Dimens.LetterSpacingMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(
            start = Dimens.SpacingXLarge, end = Dimens.SpacingXLarge,
            top = Dimens.SpacingSectionTop, bottom = Dimens.SpacingXXSmall
        ),
    )
}

@Composable
private fun DetailsGrid(movie: MovieBO) {
    val months = stringArrayResource(R.array.months_short)
    val cells = listOf(
        stringResource(R.string.label_premiere) to fmtDate(movie.releaseDate, months),
        stringResource(R.string.label_original_language) to langLabel(movie.originalLanguage),
        stringResource(R.string.label_classification) to ageLabel(movie.adult),
        stringResource(R.string.label_tmdb_id) to "#${movie.id}",
    )
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.SpacingXLarge),
        verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMediumLarge),
    ) {
        cells.chunked(2).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMediumLarge)) {
                row.forEach { (k, v) -> DetailCell(k, v, modifier = Modifier.weight(1f)) }
                if (row.size < 2) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun DetailCell(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label.uppercase(Locale.ROOT),
            fontSize = Dimens.TextXXSmall, fontWeight = FontWeight.SemiBold,
            letterSpacing = Dimens.LetterSpacingSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(Dimens.DotSeparatorSize))
        Text(
            text = value,
            fontSize = Dimens.TextMediumLarge, fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

val SampleMovie = MovieBO(
    adult = false, backdropPath = "/abc123.jpg", id = 1241982, originalLanguage = "en",
    originalTitle = "The Super Mario Galaxy Movie",
    overview = "Mario y sus amigos cruzan galaxias para detener a Bowser.",
    popularity = 1284.572, posterPath = "/xyz789.jpg", releaseDate = "2026-04-03",
    title = "The Super Mario Galaxy Movie", video = false, voteAverage = 8.4, voteCount = 3127,
)
