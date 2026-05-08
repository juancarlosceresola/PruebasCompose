package com.example.pruebascompose.data.repoInterface

import com.example.pruebascompose.data.local.Movie
import com.example.pruebascompose.data.local.PagingResult

interface MovieRepositoryInterface {
    suspend fun getMovies(): PagingResult
    suspend fun getMovieDetail(id: String): Movie
}