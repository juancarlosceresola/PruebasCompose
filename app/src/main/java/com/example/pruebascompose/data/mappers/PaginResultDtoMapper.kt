package com.example.pruebascompose.data.mappers

import com.example.pruebascompose.data.local.PagingResult
import com.example.pruebascompose.data.remotedata.dto.PagingResultDto

fun PagingResultDto.toPagingResult() = PagingResult(
    page = this.page,
    results = this.results.toMovieList(),
    totalPages = this.totalPages,
    totalResults = this.totalResults

)