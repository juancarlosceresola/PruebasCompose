package com.example.pruebascompose.domain.useCase

import com.example.pruebascompose.domain.base.UseCase
import com.example.pruebascompose.domain.model.PagingResultBO
import com.example.pruebascompose.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesTopRatedUseCase @Inject constructor(
    private val moviesRepository: MovieRepository
) : UseCase<Unit, PagingResultBO>() {
    override suspend fun useCaseFunction(input: Unit): PagingResultBO =
        moviesRepository.getTopRated()
}
