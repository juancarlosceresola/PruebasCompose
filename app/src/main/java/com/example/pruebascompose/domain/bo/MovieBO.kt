package com.example.pruebascompose.domain.bo

data class MovieBO (
    val id : Int,
    val originalLanguage : String,
    val overview : String,
    val posterPath : String,
    val releaseDate : String,
    val title : String,
    val voteAverage : Double,
)