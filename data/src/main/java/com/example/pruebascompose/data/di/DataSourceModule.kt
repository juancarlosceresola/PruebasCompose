package com.example.pruebascompose.data.di

import com.example.pruebascompose.data.api.MoviesApi
import com.example.pruebascompose.data.remotedata.AuthDataSource
import com.example.pruebascompose.data.remotedata.MovieDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(moviesApi: MoviesApi): MovieDataSource = MovieDataSource(moviesApi)

    @Provides
    @Singleton
    fun provideAuthDataSource(moviesApi: MoviesApi): AuthDataSource = AuthDataSource(moviesApi)
}
