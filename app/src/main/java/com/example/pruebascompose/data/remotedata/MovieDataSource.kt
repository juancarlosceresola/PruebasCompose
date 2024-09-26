package com.example.pruebascompose.data.remotedata

import com.example.pruebascompose.core.extensions.parseResponse
import com.example.pruebascompose.data.api.MoviesApi
import com.example.pruebascompose.data.remotedata.dto.MovieDetailDto
import com.example.pruebascompose.data.remotedata.dto.PagingResultDto

class MovieDataSource(private val moviesApi: MoviesApi) {

    suspend fun getPeliculas(): PagingResultDto {
        return moviesApi.getPeliculas().parseResponse()
    }

    suspend fun getPeliculaDetalle(
        id_Pelicula: String,
        api_key: String,
        language: String
    ): MovieDetailDto {
        return moviesApi.getPeliculaDetalle(id_Pelicula, api_key, "es").parseResponse()
    }
}