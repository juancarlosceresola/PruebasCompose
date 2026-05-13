package com.example.pruebascompose.data.repository

import com.example.pruebascompose.data.mappers.toMovieBO
import com.example.pruebascompose.data.mappers.toPagingResultBO
import com.example.pruebascompose.data.remotedata.MovieDataSource
import com.example.pruebascompose.domain.model.MovieBO
import com.example.pruebascompose.domain.model.PagingResultBO
import com.example.pruebascompose.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieDataSource: MovieDataSource
) : MovieRepository {

    override suspend fun getMovies(): PagingResultBO = movieDataSource.getPeliculas().toPagingResultBO()
    override suspend fun getMovieDetail(id: String): MovieBO = movieDataSource.getPeliculaDetalle(id, "es").toMovieBO()
    override suspend fun getTopRated(): PagingResultBO = movieDataSource.getTopRated().toPagingResultBO()
    override suspend fun getNowRating(): PagingResultBO = movieDataSource.getNowPlaying().toPagingResultBO()
    override suspend fun getUpcoming(): PagingResultBO = movieDataSource.getUpcoming().toPagingResultBO()
}
