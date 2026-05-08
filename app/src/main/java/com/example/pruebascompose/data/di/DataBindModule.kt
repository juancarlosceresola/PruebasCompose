package com.example.pruebascompose.data.di

import com.example.pruebascompose.data.repoInterface.MovieRepositoryInterface
import com.example.pruebascompose.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {

    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepository): MovieRepositoryInterface

}