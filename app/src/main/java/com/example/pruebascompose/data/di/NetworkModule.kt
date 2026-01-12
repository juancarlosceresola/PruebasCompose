package com.example.pruebascompose.data.di

import com.example.pruebascompose.data.api.FlexibleDateAdapter
import com.example.pruebascompose.data.api.MoviesApi
import com.example.pruebascompose.data.remotedata.interceptors.AuthorizationInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(FlexibleDateAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    @Named("APINetwork")
    fun provideNetwork(moshi: Moshi): MoviesApi {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClientBuilder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()

        return retrofit.create(MoviesApi::class.java)

    }
}