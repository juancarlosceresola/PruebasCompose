package com.example.pruebascompose.data.api

import com.example.pruebascompose.data.remotedata.dto.MovieDetailDto
import com.example.pruebascompose.data.remotedata.dto.PagingResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPeliculas(): Response<PagingResultDto>

    @GET("movie/{id_Pelicula}")
    suspend fun getPeliculaDetalle(@Path("id_Pelicula") id_Pelicula:String, @Query("language") language:String ): Response<MovieDetailDto>
}