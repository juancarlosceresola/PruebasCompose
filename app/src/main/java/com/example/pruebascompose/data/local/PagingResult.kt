package com.example.pruebascompose.data.local

data class PagingResult(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
