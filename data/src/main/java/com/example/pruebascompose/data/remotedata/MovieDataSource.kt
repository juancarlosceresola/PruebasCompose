package com.example.pruebascompose.data.remotedata

import com.example.pruebascompose.core.extensions.parseResponse
import com.example.pruebascompose.data.api.MoviesApi
import com.example.pruebascompose.data.remotedata.dto.MovieDetailDto
import com.example.pruebascompose.data.remotedata.dto.PagingResultDto
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val moviesApi: MoviesApi) {

    suspend fun getPeliculas(): PagingResultDto = moviesApi.getPeliculas().parseResponse()
    suspend fun getTopRated(): PagingResultDto = moviesApi.getTopFilms().parseResponse()
    suspend fun getNowPlaying(): PagingResultDto = moviesApi.getNowRating().parseResponse()
    suspend fun getUpcoming(): PagingResultDto = moviesApi.getUpcoming().parseResponse()
    suspend fun getPeliculaDetalle(id: String, language: String): MovieDetailDto =
        moviesApi.getPeliculaDetalle(id, "es").parseResponse()
}
