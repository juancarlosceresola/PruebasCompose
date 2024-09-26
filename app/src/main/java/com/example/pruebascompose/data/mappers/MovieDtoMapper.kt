package com.example.pruebascompose.data.mappers

import com.example.pruebascompose.data.local.Movie
import com.example.pruebascompose.data.remotedata.MovieDto

fun MovieDto.toMovie() = Movie(
    adult = this.adult,
    poster_path = this.poster_path,
    original_language = this.original_language,
    title = this.title,
    vote_average = this.vote_average,
    overview = this.overview,
    release_date = this.release_date,
    genre_ids = this.genre_ids,
    original_title = this.original_title,
    video = this.video,
    vote_count = this.vote_count,
    backdrop_path = this.backdrop_path,
    id = this.id,
    popularity = this.popularity
)
