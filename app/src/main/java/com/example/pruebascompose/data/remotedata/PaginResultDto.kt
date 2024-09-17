package com.example.pruebascompose.data.remotedata

import com.squareup.moshi.Json

data class PagingResultDto (
    @Json(name="page") val page : Int,
    @Json(name="results") val results : List<MovieDto>,
    @Json(name="total_pages") val total_pages : Int,
    @Json(name="total_results") val total_results : Int
)