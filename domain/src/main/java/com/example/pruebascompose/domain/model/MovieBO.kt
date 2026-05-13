package com.example.pruebascompose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieBO(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val adult: Boolean,
    val video: Boolean
)
