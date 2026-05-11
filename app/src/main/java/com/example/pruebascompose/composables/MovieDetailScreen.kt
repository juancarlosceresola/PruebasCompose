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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pruebascompose.data.local.Movie
import java.util.Locale




// TMDB sirve rutas relativas: combínalas con el base url al renderizar.
private const val TMDB_IMG = "https://image.tmdb.org/t/p"
private fun posterUrl(path: String, size: String = "w500") = "$TMDB_IMG/$size$path"
private fun backdropUrl(path: String, size: String = "w780") = "$TMDB_IMG/$size$path"


private fun fmtYear(date: String) = date.take(4)
private fun fmtCount(n: Int): String =
    if (n >= 1000) "%.1f".format(n / 1000.0).removeSuffix(".0") + "k" else n.toString()
private fun fmtPopularity(p: Double) = "%,d".format(p.toInt())
private fun langLabel(code: String) = when (code) {
    "en" -> "Inglés"; "es" -> "Español"; "fr" -> "Francés"
    "ja" -> "Japonés"; "ko" -> "Coreano"; else -> code.uppercase(Locale.ROOT)
}
private fun ageLabel(adult: Boolean) = if (adult) "+18" else "TP"
private fun fmtDate(date: String): String {
    val parts = date.split("-")
    if (parts.size != 3) return date
    val months = listOf("ene","feb","mar","abr","may","jun","jul","ago","sep","oct","nov","dic")
    val d = parts[2].toIntOrNull() ?: return date
    val m = parts[1].toIntOrNull()?.minus(1)?.let { months.getOrNull(it) } ?: return date
    return "$d $m ${parts[0]}"
}


@Composable
fun MovieDetailScreen(
    movie: Movie,
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
            Spacer(Modifier.height(16.dp))
            RatingRow(movie)
            Spacer(Modifier.height(16.dp))
            ActionButtons(onPlayTrailer, onAddToList)
            SectionHeader("Sinopsis")
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 18.dp),
            )
            SectionHeader("Detalles")
            DetailsGrid(movie)
            Spacer(Modifier.height(32.dp))
        }

        // Top bar flotante (sobre el backdrop)
        TopActions(
            onBack = { onBack() },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
        )
    }
}


@Composable
private fun BackdropWithPoster(movie: Movie) {
    Box {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 10f),
        ) {
            AsyncImage(
                model = backdropUrl(movie.backdrop_path),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                colorFilter = ColorFilter.tint(
                    Color.Black.copy(alpha = 0.35f),
                    androidx.compose.ui.graphics.BlendMode.Darken,
                ),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0.0f to Color.Transparent,
                            0.6f to Color.Transparent,
                            1.0f to MaterialTheme.colorScheme.background,
                        ),
                    ),
            )
        }

        // Póster + cabecera
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp)
                .offset(y = (20).dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            AsyncImage(
                model = movie.poster_path,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(116.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 6.dp),
            ) {
                Text(
                    text = "${fmtYear(movie.release_date)} · ${langLabel(movie.original_language)} · ${ageLabel(movie.adult)}",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 22.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                if (movie.original_title != movie.title) {
                    Text(
                        text = movie.original_title,
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                    )
                }
            }
        }
        // Compensa el offset negativo del Row para que el siguiente bloque siga normal
        Spacer(modifier = Modifier.height(0.dp))
    }
}


@Composable
private fun TopActions(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconCircleButton(onClick = {onBack()}) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            IconCircleButton({}) { Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorito", tint = Color.White) }
            IconCircleButton({}) { Icon(Icons.Filled.Share, contentDescription = "Compartir", tint = Color.White) }
        }
    }
}

@Composable
private fun IconCircleButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable(onClick = {onClick()}),
        contentAlignment = Alignment.Center,

    ) { content() }
}


@Composable
private fun RatingRow(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.Star, contentDescription = null,
                tint = Color(0xFFF5A524), modifier = Modifier.size(22.dp),
            )
            Spacer(Modifier.width(6.dp))
            Column {
                Text(
                    text = "%.1f".format(movie.vote_average),
                    fontWeight = FontWeight.Bold, fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "${fmtCount(movie.vote_count)} votos",
                    fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "POPULARIDAD",
                fontSize = 10.sp, fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = "↗ ${fmtPopularity(movie.popularity)}",
                fontSize = 14.sp, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}


@Composable
private fun ActionButtons(onPlayTrailer: () -> Unit, onAddToList: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Button(
            onClick = onPlayTrailer,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Icon(Icons.Filled.PlayArrow, contentDescription = null)
            Spacer(Modifier.width(6.dp))
            Text("Ver tráiler", fontWeight = FontWeight.Bold)
        }
        OutlinedButton(
            onClick = onAddToList,
            modifier = Modifier.height(48.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text("+ Mi lista", fontWeight = FontWeight.SemiBold)
        }
    }
}


@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text.uppercase(Locale.ROOT),
        fontSize = 11.sp, fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 24.dp, bottom = 10.dp),
    )
}

@Composable
private fun DetailsGrid(movie: Movie) {
    val cells = listOf(
        "Estreno" to fmtDate(movie.release_date),
        "Idioma original" to langLabel(movie.original_language),
        "Clasificación" to ageLabel(movie.adult),
        "TMDB ID" to "#${movie.id}",
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        cells.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                row.forEach { (k, v) ->
                    DetailCell(k, v, modifier = Modifier.weight(1f))
                }
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
            fontSize = 10.sp, fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.6.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = value,
            fontSize = 13.5.sp, fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}


val SampleMovie = Movie(
    adult = false,
    backdrop_path = "/abc123.jpg",
    id = 1241982,
    original_language = "en",
    original_title = "The Super Mario Galaxy Movie",
    overview = "Mario y sus amigos cruzan galaxias para detener a Bowser y devolver la corona perdida de la Princesa Peach.",
    popularity = 1284.572,
    poster_path = "/xyz789.jpg",
    release_date = "2026-04-03",
    title = "The Super Mario Galaxy Movie",
    video = false,
    vote_average = 8.4,
    vote_count = 3127,
)
