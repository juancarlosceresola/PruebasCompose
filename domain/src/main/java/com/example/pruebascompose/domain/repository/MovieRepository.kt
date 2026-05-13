package com.example.pruebascompose.domain.repository

import com.example.pruebascompose.domain.model.MovieBO
import com.example.pruebascompose.domain.model.PagingResultBO

interface MovieRepository {
    suspend fun getMovies(): PagingResultBO
    suspend fun getMovieDetail(id: String): MovieBO
    suspend fun getTopRated(): PagingResultBO
    suspend fun getNowRating(): PagingResultBO
    suspend fun getUpcoming(): PagingResultBO
}
