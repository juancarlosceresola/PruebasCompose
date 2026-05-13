package com.example.pruebascompose.data.di

import com.example.pruebascompose.data.repository.AuthRepositoryImpl
import com.example.pruebascompose.data.repository.MovieRepositoryImpl
import com.example.pruebascompose.domain.repository.AuthRepository
import com.example.pruebascompose.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
