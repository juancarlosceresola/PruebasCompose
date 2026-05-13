package com.example.pruebascompose.domain.model

data class PagingResultBO(
    val page: Int,
    val movies: List<MovieBO>,
    val totalPages: Int,
    val totalResults: Int
)
