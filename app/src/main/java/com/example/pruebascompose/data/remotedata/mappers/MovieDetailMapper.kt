package com.example.pruebascompose.data.remotedata.mappers

import com.example.pruebascompose.data.remotedata.dto.MovieDetailDto
import com.example.pruebascompose.domain.bo.MovieBO

fun MovieDetailDto.toMovieDetailBO() = MovieBO(
    id = this.id ?: -1,
    originalLanguage = this.original_language,
    overview = this.overview,
    posterPath = this.poster_path,
    releaseDate = this.release_date,
    title= this.title,
    voteAverage = this.vote_average
)