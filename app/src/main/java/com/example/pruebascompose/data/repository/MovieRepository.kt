package com.example.pruebascompose.data.repository

import com.example.pruebascompose.data.local.Movie
import com.example.pruebascompose.data.local.PagingResult
import com.example.pruebascompose.data.mappers.toMovie
import com.example.pruebascompose.data.mappers.toPagingResult
import com.example.pruebascompose.data.remotedata.MovieDataSource
import com.example.pruebascompose.data.repoInterface.MovieRepositoryInterface

class MovieRepository(
    private val movieDataSource: MovieDataSource
):MovieRepositoryInterface {
    override suspend fun getMovies(): PagingResult {
        return movieDataSource.getPeliculas().toPagingResult()
    }

    override suspend fun getMovieDetail(id: String): Movie {
      return movieDataSource.getPeliculaDetalle(id,"Es").toMovie()
    }
}