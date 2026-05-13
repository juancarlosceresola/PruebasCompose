package com.example.movies.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pruebascompose.R
import com.example.pruebascompose.domain.model.MovieBO
import com.example.pruebascompose.ui.theme.CinemaBackground
import com.example.pruebascompose.ui.theme.Dimens
import com.example.pruebascompose.ui.theme.EditorialAccent
import com.example.pruebascompose.ui.theme.OverlayBlack55
import com.example.pruebascompose.ui.theme.OverlayWhite08
import com.example.pruebascompose.ui.theme.OverlayWhite10
import com.example.pruebascompose.ui.theme.OverlayWhite12
import com.example.pruebascompose.ui.theme.OverlayWhite18
import com.example.pruebascompose.ui.theme.OverlayWhite22
import com.example.pruebascompose.ui.theme.OverlayWhite40
import com.example.pruebascompose.ui.theme.OverlayWhite55
import com.example.pruebascompose.ui.theme.OverlayWhite60
import com.example.pruebascompose.ui.theme.OverlayWhite78
import com.example.pruebascompose.ui.theme.OverlayWhite82
import com.example.pruebascompose.ui.theme.OverlayWhite90
import com.example.pruebascompose.ui.theme.StarYellow
import java.util.Locale

private const val TMDB_IMG_V = "https://image.tmdb.org/t/p"
private fun posterUrlV(path: String, size: String = "w500") = "$TMDB_IMG_V/$size$path"
private fun backdropUrlV(path: String, size: String = "w780") = "$TMDB_IMG_V/$size$path"

private fun fmtYearV(date: String) = date.take(4)
private fun fmtCountV(n: Int): String =
    if (n >= 1000) "%.1f".format(n / 1000.0).removeSuffix(".0") + "k" else n.toString()
private fun fmtPopularityV(p: Double) = "%,d".format(p.toInt())
private fun fmtDateV(date: String, months: Array<String>): String {
    val p = date.split("-")
    if (p.size != 3) return date
    val d = p[2].toIntOrNull() ?: return date
    val m = p[1].toIntOrNull()?.minus(1)?.let { months.getOrNull(it) } ?: return date
    return "$d $m ${p[0]}"
}

@Composable
private fun langLabelV(code: String): String = when (code) {
    "en" -> stringResource(R.string.lang_english)
    "es" -> stringResource(R.string.lang_spanish)
    "fr" -> stringResource(R.string.lang_french)
    "ja" -> stringResource(R.string.lang_japanese)
    "ko" -> stringResource(R.string.lang_korean)
    else -> code.uppercase(Locale.ROOT)
}

@Composable
private fun ageLabelV(adult: Boolean): String =
    if (adult) stringResource(R.string.classification_adults_short)
    else stringResource(R.string.classification_all_short)


@Composable
fun MovieDetailScreenCinematic(
    movie: MovieBO,
    onBack: () -> Unit = {},
    onPlay: () -> Unit = {},
    onAddToList: () -> Unit = {},
    onShare: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val scroll = rememberScrollState()
    val months = stringArrayResource(R.array.months_short)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CinemaBackground),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.70f),
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().background(Color.Black),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0.00f to CinemaBackground.copy(alpha = 0.55f),
                            0.18f to Color.Transparent,
                            0.38f to Color.Transparent,
                            0.62f to CinemaBackground.copy(alpha = 0.85f),
                            0.78f to CinemaBackground,
                        ),
                    ),
            )
        }

        Column(modifier = Modifier.fillMaxSize().verticalScroll(scroll)) {
            Spacer(modifier = Modifier.height(Dimens.CinematicHeroSpacer))

            Column(modifier = Modifier.padding(start = Dimens.SpacingSection, end = Dimens.SpacingSection, bottom = Dimens.SpacingBottom)) {
                Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingXSmall)) {
                    DarkChip(fmtYearV(movie.releaseDate))
                    DarkChip(langLabelV(movie.originalLanguage))
                    DarkChip(ageLabelV(movie.adult))
                    DarkChip(stringResource(R.string.label_hd))
                }

                Spacer(Modifier.height(Dimens.SpacingMediumLarge))
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = Dimens.TextSectionTitle,
                    fontWeight = FontWeight.Black,
                    lineHeight = Dimens.LineHeightLarge,
                )

                Spacer(Modifier.height(Dimens.SpacingMedium))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, null, tint = StarYellow, modifier = Modifier.size(Dimens.SpacingMediumLarge))
                    Spacer(Modifier.width(5.dp))
                    Text("%.1f".format(movie.voteAverage), color = Color.White, fontSize = Dimens.TextMediumSmall, fontWeight = FontWeight.Bold)
                    Text(stringResource(R.string.score_out_of_10), color = OverlayWhite60, fontSize = Dimens.TextMediumSmall)
                    DotSep()
                    Text(stringResource(R.string.votes_format, fmtCountV(movie.voteCount)), color = OverlayWhite78, fontSize = Dimens.TextMediumSmall)
                    DotSep()
                    Text(stringResource(R.string.popularity_format, fmtPopularityV(movie.popularity)), color = OverlayWhite78, fontSize = Dimens.TextMediumSmall)
                }

                Spacer(Modifier.height(Dimens.SpacingXLarge))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = onPlay,
                        modifier = Modifier.weight(1f).height(Dimens.ButtonHeight),
                        shape = RoundedCornerShape(Dimens.CornerPill),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    ) {
                        Icon(Icons.Filled.PlayArrow, null)
                        Spacer(Modifier.width(Dimens.SpacingXSmall))
                        Text(stringResource(R.string.action_play), fontWeight = FontWeight.ExtraBold)
                    }
                    GlassCircleButton(onClick = onAddToList) {
                        Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.action_add_to_list), tint = Color.White)
                    }
                    GlassCircleButton(onClick = onShare) {
                        Icon(Icons.Filled.Share, contentDescription = stringResource(R.string.action_share), tint = Color.White)
                    }
                }

                Spacer(Modifier.height(Dimens.SpacingSection))
                Text(text = movie.overview, color = OverlayWhite82, fontSize = Dimens.TextBody, lineHeight = Dimens.LineHeightMedium)

                Spacer(Modifier.height(Dimens.SpacingSection))
                val rows = listOf(
                    stringResource(R.string.label_original_title) to movie.originalTitle,
                    stringResource(R.string.label_premiere_date) to fmtDateV(movie.releaseDate, months),
                    stringResource(R.string.label_original_language) to langLabelV(movie.originalLanguage),
                    stringResource(R.string.label_classification) to if (movie.adult) stringResource(R.string.classification_adults_long) else stringResource(R.string.classification_all),
                    stringResource(R.string.label_score) to "${"%.1f".format(movie.voteAverage)}${stringResource(R.string.score_out_of_10)} — ${stringResource(R.string.votes_format, fmtCountV(movie.voteCount))}",
                    stringResource(R.string.label_popularity_stat) to fmtPopularityV(movie.popularity),
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    HorizontalDivider(color = OverlayWhite10)
                    rows.forEach { (k, v) ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = Dimens.SpacingMedium),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(k, color = OverlayWhite55, fontSize = Dimens.TextMedium)
                            Text(v, color = Color.White, fontSize = Dimens.TextMedium, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = Dimens.SpacingMediumLarge))
                        }
                        HorizontalDivider(color = OverlayWhite08)
                    }
                }
            }
        }

        Row(
            modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth().padding(Dimens.SpacingSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            GlassCircleButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, stringResource(R.string.action_back), tint = Color.White) }
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingXSmall)) {
                GlassCircleButton(onClick = {}) { Icon(Icons.Filled.FavoriteBorder, stringResource(R.string.action_favorite), tint = Color.White) }
                GlassCircleButton(onClick = onShare) { Icon(Icons.Filled.Share, stringResource(R.string.action_share), tint = Color.White) }
            }
        }
    }
}

@Composable
private fun DarkChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(Dimens.CornerPill))
            .background(OverlayWhite12)
            .border(1.dp, OverlayWhite18, RoundedCornerShape(Dimens.CornerPill))
            .padding(horizontal = Dimens.SpacingChip, vertical = Dimens.ChipPaddingVertical),
    ) {
        Text(text, color = OverlayWhite90, fontSize = Dimens.TextSmall, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun DotSep() {
    Box(
        modifier = Modifier
            .padding(horizontal = Dimens.SpacingSmall)
            .size(Dimens.DotSeparatorSize)
            .clip(CircleShape)
            .background(OverlayWhite40),
    )
}

@Composable
private fun GlassCircleButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = OverlayWhite12,
        border = BorderStroke(1.dp, OverlayWhite22),
        modifier = Modifier.size(Dimens.ButtonHeight),
    ) {
        Box(contentAlignment = Alignment.Center) { content() }
    }
}


@Composable
fun MovieDetailScreenEditorial(
    movie: MovieBO,
    onBack: () -> Unit = {},
    onPlayTrailer: () -> Unit = {},
    serifFamily: FontFamily = FontFamily.Serif,
    accent: Color = EditorialAccent,
    modifier: Modifier = Modifier,
) {
    val scroll = rememberScrollState()
    val months = stringArrayResource(R.array.months_short)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(scroll),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(Dimens.SpacingSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, stringResource(R.string.action_back)) }
            Row {
                IconButton(onClick = {}) { Icon(Icons.Filled.FavoriteBorder, stringResource(R.string.action_favorite)) }
                IconButton(onClick = {}) { Icon(Icons.Filled.Share, stringResource(R.string.action_share)) }
            }
        }

        Column(modifier = Modifier.padding(horizontal = Dimens.SpacingSection).padding(bottom = Dimens.SpacingBottom)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(Dimens.CornerOverlay))
                    .background(Color.Black),
            ) {
                AsyncImage(
                    model = movie.posterPath,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Row(
                    modifier = Modifier.align(Alignment.TopStart).padding(Dimens.SpacingMedium),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingXSmall),
                ) {
                    OverlayChip(ageLabelV(movie.adult))
                    OverlayChip(stringResource(R.string.label_hd))
                }
            }

            Spacer(Modifier.height(Dimens.SpacingSection))
            Text(
                text = "${fmtYearV(movie.releaseDate)} · ${langLabelV(movie.originalLanguage)}".uppercase(Locale.ROOT),
                color = accent, fontSize = Dimens.TextSmall, fontWeight = FontWeight.Bold, letterSpacing = Dimens.LetterSpacingLarge,
            )

            Spacer(Modifier.height(Dimens.SpacingXSmall))
            Text(
                text = movie.title,
                fontFamily = serifFamily, fontSize = Dimens.TextSectionTitle,
                fontWeight = FontWeight.SemiBold, lineHeight = Dimens.LineHeightLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if (movie.originalTitle != movie.title) {
                Spacer(Modifier.height(Dimens.SpacingXXSmall))
                Text(
                    text = stringResource(R.string.original_title_prefix, movie.originalTitle),
                    fontSize = Dimens.TextMedium, fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(Modifier.height(Dimens.SpacingMediumLarge))
            HorizontalDivider()
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = Dimens.SpacingLarge),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StatBlock(
                    big = "%.1f".format(movie.voteAverage),
                    small = stringResource(R.string.label_out_of_10),
                    leadingIcon = { Icon(Icons.Filled.Star, null, tint = StarYellow, modifier = Modifier.size(Dimens.SpacingXXLarge)) },
                )
                VDivider()
                StatBlock(big = fmtCountV(movie.voteCount), small = stringResource(R.string.label_votes))
                VDivider()
                StatBlock(big = fmtPopularityV(movie.popularity), small = stringResource(R.string.label_popularity_stat))
            }
            HorizontalDivider()

            Spacer(Modifier.height(Dimens.SpacingXXLarge))
            val first = movie.overview.firstOrNull()?.toString() ?: ""
            val rest = movie.overview.drop(1)
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = first, fontFamily = serifFamily,
                    fontSize = Dimens.TextDropCap, fontWeight = FontWeight.Bold,
                    color = accent, lineHeight = Dimens.LineHeightDropCap,
                    modifier = Modifier.padding(end = Dimens.SpacingXSmall, top = Dimens.SpacingXXSmall),
                )
                Text(text = rest, fontSize = Dimens.TextBodyLarge, lineHeight = Dimens.LineHeightStandard, color = MaterialTheme.colorScheme.onSurface)
            }

            Spacer(Modifier.height(Dimens.SpacingBlock))
            val items = listOf(
                stringResource(R.string.label_premiere) to fmtDateV(movie.releaseDate, months),
                stringResource(R.string.label_original_language) to langLabelV(movie.originalLanguage),
                stringResource(R.string.label_classification) to if (movie.adult) stringResource(R.string.classification_adults) else stringResource(R.string.classification_all),
                stringResource(R.string.label_id) to "#${movie.id}",
            )
            items.forEach { (k, v) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(k, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = Dimens.TextMediumLarge)
                    Text(v, color = MaterialTheme.colorScheme.onSurface, fontSize = Dimens.TextMediumLarge, fontWeight = FontWeight.SemiBold)
                }
                HorizontalDivider()
            }

            Spacer(Modifier.height(Dimens.SpacingSection))
            Button(
                onClick = onPlayTrailer,
                modifier = Modifier.fillMaxWidth().height(Dimens.CtaButtonHeight),
                shape = RoundedCornerShape(Dimens.CornerCard),
                colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = Color.White),
            ) {
                Icon(Icons.Filled.PlayArrow, null)
                Spacer(Modifier.width(Dimens.SpacingSmall))
                Text(stringResource(R.string.action_watch_trailer), fontWeight = FontWeight.Bold, fontSize = Dimens.TextButton)
            }
        }
    }
}

@Composable
private fun OverlayChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(Dimens.CornerPill))
            .background(OverlayBlack55)
            .padding(horizontal = Dimens.SpacingChip, vertical = Dimens.ChipPaddingVertical),
    ) {
        Text(text, color = Color.White, fontSize = Dimens.TextSmall, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun RowScope.StatBlock(
    big: String,
    small: String,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
        if (leadingIcon != null) { leadingIcon(); Spacer(Modifier.width(Dimens.SpacingSmall)) }
        Column {
            Text(big, fontSize = Dimens.TextStatNumber, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onSurface)
            Text(small, fontSize = Dimens.TextXSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun VDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(Dimens.StatDividerHeight)
            .background(MaterialTheme.colorScheme.outlineVariant),
    )
}
