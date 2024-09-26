package com.example.pruebascompose.data.local

import com.example.pruebascompose.data.remotedata.dto.MovieDetailDto

data class PagingResult(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
