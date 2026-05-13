package com.example.pruebascompose.data.mappers

import com.example.pruebascompose.data.remotedata.dto.MovieDetailDto
import com.example.pruebascompose.domain.model.MovieBO

fun MovieDetailDto.toMovieBO() = MovieBO(
    id = this.id,
    title = this.title,
    originalTitle = this.original_title,
    originalLanguage = this.original_language,
    overview = this.overview,
    posterPath = "https://image.tmdb.org/t/p/original" + this.poster_path,
    backdropPath = this.backdrop_path,
    releaseDate = this.release_date,
    popularity = this.popularity,
    voteAverage = this.vote_average,
    voteCount = this.vote_count,
    adult = this.adult,
    video = this.video
)

fun List<MovieDetailDto>?.toMovieBOList() = this?.map { it.toMovieBO() } ?: listOf()
