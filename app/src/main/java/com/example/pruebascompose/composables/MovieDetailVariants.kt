package com.example.movies.ui.detail

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pruebascompose.domain.model.MovieBO
import java.util.Locale

private const val TMDB_IMG_V = "https://image.tmdb.org/t/p"
private fun posterUrlV(path: String, size: String = "w500") = "$TMDB_IMG_V/$size$path"
private fun backdropUrlV(path: String, size: String = "w780") = "$TMDB_IMG_V/$size$path"

private fun fmtYearV(date: String) = date.take(4)
private fun fmtCountV(n: Int): String =
    if (n >= 1000) "%.1f".format(n / 1000.0).removeSuffix(".0") + "k" else n.toString()
private fun fmtPopularityV(p: Double) = "%,d".format(p.toInt())
private fun langLabelV(code: String) = when (code) {
    "en" -> "Inglés"; "es" -> "Español"; "fr" -> "Francés"
    "ja" -> "Japonés"; "ko" -> "Coreano"; else -> code.uppercase(Locale.ROOT)
}
private fun ageLabelV(adult: Boolean) = if (adult) "+18" else "TP"
private fun fmtDateV(date: String): String {
    val p = date.split("-"); if (p.size != 3) return date
    val months = listOf("ene","feb","mar","abr","may","jun","jul","ago","sep","oct","nov","dic")
    val d = p[2].toIntOrNull() ?: return date
    val m = p[1].toIntOrNull()?.minus(1)?.let { months.getOrNull(it) } ?: return date
    return "$d $m ${p[0]}"
}


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
    val bgDark = Color(0xFF0E0B14)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(bgDark),
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
                            0.00f to bgDark.copy(alpha = 0.55f),
                            0.18f to Color.Transparent,
                            0.38f to Color.Transparent,
                            0.62f to bgDark.copy(alpha = 0.85f),
                            0.78f to bgDark,
                        ),
                    ),
            )
        }

        Column(modifier = Modifier.fillMaxSize().verticalScroll(scroll)) {
            Spacer(modifier = Modifier.height(360.dp))

            Column(modifier = Modifier.padding(start = 22.dp, end = 22.dp, bottom = 32.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    DarkChip(fmtYearV(movie.releaseDate))
                    DarkChip(langLabelV(movie.originalLanguage))
                    DarkChip(ageLabelV(movie.adult))
                    DarkChip("HD")
                }

                Spacer(Modifier.height(14.dp))
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 32.sp,
                )

                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, null, tint = Color(0xFFF5A524), modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(5.dp))
                    Text("%.1f".format(movie.voteAverage), color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(" / 10", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                    DotSep()
                    Text("${fmtCountV(movie.voteCount)} votos", color = Color.White.copy(alpha = 0.78f), fontSize = 12.sp)
                    DotSep()
                    Text("↗ ${fmtPopularityV(movie.popularity)}", color = Color.White.copy(alpha = 0.78f), fontSize = 12.sp)
                }

                Spacer(Modifier.height(18.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = onPlay,
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(999.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    ) {
                        Icon(Icons.Filled.PlayArrow, null)
                        Spacer(Modifier.width(6.dp))
                        Text("Reproducir", fontWeight = FontWeight.ExtraBold)
                    }
                    GlassCircleButton(onClick = onAddToList) {
                        Icon(Icons.Filled.Add, contentDescription = "Mi lista", tint = Color.White)
                    }
                    GlassCircleButton(onClick = onShare) {
                        Icon(Icons.Filled.Share, contentDescription = "Compartir", tint = Color.White)
                    }
                }

                Spacer(Modifier.height(22.dp))
                Text(text = movie.overview, color = Color.White.copy(alpha = 0.82f), fontSize = 14.sp, lineHeight = 21.sp)

                Spacer(Modifier.height(22.dp))
                val rows = listOf(
                    "Título original" to movie.originalTitle,
                    "Fecha de estreno" to fmtDateV(movie.releaseDate),
                    "Idioma original" to langLabelV(movie.originalLanguage),
                    "Clasificación" to if (movie.adult) "Solo adultos (+18)" else "Todo público",
                    "Puntuación" to "%.1f / 10 — ${fmtCountV(movie.voteCount)} votos".format(movie.voteAverage),
                    "Popularidad" to fmtPopularityV(movie.popularity),
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Divider(color = Color.White.copy(alpha = 0.10f))
                    rows.forEach { (k, v) ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(k, color = Color.White.copy(alpha = 0.55f), fontSize = 13.sp)
                            Text(v, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 14.dp))
                        }
                        Divider(color = Color.White.copy(alpha = 0.08f))
                    }
                }
            }
        }

        Row(
            modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            GlassCircleButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, "Volver", tint = Color.White) }
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                GlassCircleButton(onClick = {}) { Icon(Icons.Filled.FavoriteBorder, "Favorito", tint = Color.White) }
                GlassCircleButton(onClick = onShare) { Icon(Icons.Filled.Share, "Compartir", tint = Color.White) }
            }
        }
    }
}

@Composable
private fun DarkChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(999.dp))
            .padding(horizontal = 9.dp, vertical = 4.dp),
    ) {
        Text(text, color = Color.White.copy(alpha = 0.9f), fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun DotSep() {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(3.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.4f)),
    )
}

@Composable
private fun GlassCircleButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = Color.White.copy(alpha = 0.12f),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.22f)),
        modifier = Modifier.size(48.dp),
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
    accent: Color = Color(0xFF5B3FE4),
    modifier: Modifier = Modifier,
) {
    val scroll = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(scroll),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, "Volver") }
            Row {
                IconButton(onClick = {}) { Icon(Icons.Filled.FavoriteBorder, "Favorito") }
                IconButton(onClick = {}) { Icon(Icons.Filled.Share, "Compartir") }
            }
        }

        Column(modifier = Modifier.padding(horizontal = 22.dp).padding(bottom = 32.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.Black),
            ) {
                AsyncImage(
                    model = movie.posterPath,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Row(
                    modifier = Modifier.align(Alignment.TopStart).padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    OverlayChip(ageLabelV(movie.adult))
                    OverlayChip("HD")
                }
            }

            Spacer(Modifier.height(22.dp))
            Text(
                text = "${fmtYearV(movie.releaseDate)} · ${langLabelV(movie.originalLanguage)}".uppercase(Locale.ROOT),
                color = accent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.4.sp,
            )

            Spacer(Modifier.height(6.dp))
            Text(
                text = movie.title,
                fontFamily = serifFamily, fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold, lineHeight = 32.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if (movie.originalTitle != movie.title) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Título original: ${movie.originalTitle}",
                    fontSize = 13.sp, fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(Modifier.height(14.dp))
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StatBlock(
                    big = "%.1f".format(movie.voteAverage),
                    small = "de 10",
                    leadingIcon = { Icon(Icons.Filled.Star, null, tint = Color(0xFFF5A524), modifier = Modifier.size(20.dp)) },
                )
                VDivider()
                StatBlock(big = fmtCountV(movie.voteCount), small = "votos")
                VDivider()
                StatBlock(big = fmtPopularityV(movie.popularity), small = "popularidad")
            }
            Divider()

            Spacer(Modifier.height(20.dp))
            val first = movie.overview.firstOrNull()?.toString() ?: ""
            val rest = movie.overview.drop(1)
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = first, fontFamily = serifFamily,
                    fontSize = 44.sp, fontWeight = FontWeight.Bold,
                    color = accent, lineHeight = 40.sp,
                    modifier = Modifier.padding(end = 6.dp, top = 4.dp),
                )
                Text(text = rest, fontSize = 15.5.sp, lineHeight = 23.sp, color = MaterialTheme.colorScheme.onSurface)
            }

            Spacer(Modifier.height(26.dp))
            val items = listOf(
                "Estreno" to fmtDateV(movie.releaseDate),
                "Idioma original" to langLabelV(movie.originalLanguage),
                "Clasificación" to if (movie.adult) "Solo adultos" else "Todo público",
                "ID" to "#${movie.id}",
            )
            items.forEach { (k, v) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(k, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.5.sp)
                    Text(v, color = MaterialTheme.colorScheme.onSurface, fontSize = 13.5.sp, fontWeight = FontWeight.SemiBold)
                }
                Divider()
            }

            Spacer(Modifier.height(22.dp))
            Button(
                onClick = onPlayTrailer,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = Color.White),
            ) {
                Icon(Icons.Filled.PlayArrow, null)
                Spacer(Modifier.width(8.dp))
                Text("Ver tráiler", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
        }
    }
}

@Composable
private fun OverlayChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color.Black.copy(alpha = 0.55f))
            .padding(horizontal = 9.dp, vertical = 4.dp),
    ) {
        Text(text, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun RowScope.StatBlock(
    big: String,
    small: String,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
        if (leadingIcon != null) { leadingIcon(); Spacer(Modifier.width(8.dp)) }
        Column {
            Text(big, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onSurface)
            Text(small, fontSize = 10.5.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun VDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(36.dp)
            .background(MaterialTheme.colorScheme.outlineVariant),
    )
}
