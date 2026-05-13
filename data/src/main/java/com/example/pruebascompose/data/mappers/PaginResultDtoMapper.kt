package com.example.pruebascompose.data.mappers

import com.example.pruebascompose.data.remotedata.dto.PagingResultDto
import com.example.pruebascompose.domain.model.PagingResultBO

fun PagingResultDto.toPagingResultBO() = PagingResultBO(
    page = this.page,
    movies = this.results.toMovieBOList(),
    totalPages = this.totalPages,
    totalResults = this.totalResults
)
