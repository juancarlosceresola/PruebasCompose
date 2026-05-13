package com.example.pruebascompose.data.di

import com.example.pruebascompose.data.remotedata.AuthDataSource
import com.example.pruebascompose.data.remotedata.MovieDataSource
import com.example.pruebascompose.data.repository.AuthRepositoryImpl
import com.example.pruebascompose.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepositoryImpl =
        MovieRepositoryImpl(movieDataSource)

    @Provides
    @Singleton
    fun provideAuthRepository(authDataSource: AuthDataSource): AuthRepositoryImpl =
        AuthRepositoryImpl(authDataSource)
}
