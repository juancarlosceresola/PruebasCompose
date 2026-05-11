package com.example.pruebascompose.domain.useCase

import com.example.pruebascompose.data.local.Movie
import com.example.pruebascompose.data.local.PagingResult
import com.example.pruebascompose.data.repoInterface.MovieRepositoryInterface
import com.example.pruebascompose.data.repository.MovieRepository
import com.example.pruebascompose.domain.base.UseCase
import javax.inject.Inject

class GetMoviesTopRatedUseCase @Inject constructor(
    private val moviesRepository: MovieRepositoryInterface
): UseCase<Unit, PagingResult>() {
    override suspend fun useCaseFunction(input: Unit): PagingResult {
        return moviesRepository.getTopRated()
    }

}