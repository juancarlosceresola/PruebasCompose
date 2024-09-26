package com.example.pruebascompose.data.remotedata.dto

import com.squareup.moshi.Json

data class PagingResultDto (
    @Json(name="page") val page : Int,
    @Json(name="results") val results : List<MovieDetailDto>,
    @Json(name="total_pages") val totalPages : Int,
    @Json(name="total_results") val totalResults : Int
)