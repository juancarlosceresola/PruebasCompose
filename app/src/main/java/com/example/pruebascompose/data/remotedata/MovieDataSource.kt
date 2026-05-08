package com.example.pruebascompose.data.remotedata

import com.example.pruebascompose.core.extensions.parseResponse
import com.example.pruebascompose.data.api.MoviesApi
import com.example.pruebascompose.data.remotedata.dto.MovieDetailDto
import com.example.pruebascompose.data.remotedata.dto.PagingResultDto
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val moviesApi: MoviesApi) {

    suspend fun getPeliculas(): PagingResultDto {
        return moviesApi.getPeliculas().parseResponse()
    }

    suspend fun getPeliculaDetalle(
        id_Pelicula: String,
        language: String
    ): MovieDetailDto {
        return moviesApi.getPeliculaDetalle(id_Pelicula, "es").parseResponse()
    }
}