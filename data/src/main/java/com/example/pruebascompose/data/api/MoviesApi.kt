package com.example.pruebascompose.data.api

import com.example.pruebascompose.data.remotedata.dto.LoginRequestDto
import com.example.pruebascompose.data.remotedata.dto.MovieDetailDto
import com.example.pruebascompose.data.remotedata.dto.PagingResultDto
import com.example.pruebascompose.data.remotedata.dto.RequestTokenDto
import com.example.pruebascompose.data.remotedata.dto.SessionDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPeliculas(): Response<PagingResultDto>

    @GET("movie/top_rated")
    suspend fun getTopFilms(): Response<PagingResultDto>

    @GET("movie/now_playing")
    suspend fun getNowRating(): Response<PagingResultDto>

    @GET("movie/upcoming")
    suspend fun getUpcoming(): Response<PagingResultDto>

    @GET("movie/{id_Pelicula}")
    suspend fun getPeliculaDetalle(
        @Path("id_Pelicula") id_Pelicula: String,
        @Query("language") language: String
    ): Response<MovieDetailDto>

    @GET("authentication/token/new")
    suspend fun getRequestToken(): Response<RequestTokenDto>

    @POST("authentication/token/validate_with_login")
    suspend fun validateWithLogin(@Body loginRequest: LoginRequestDto): Response<RequestTokenDto>

    @POST("authentication/session/new")
    suspend fun createSession(@Body body: Map<String, String>): Response<SessionDto>
}
