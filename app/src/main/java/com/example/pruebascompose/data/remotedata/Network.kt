package com.example.pruebascompose.data.remotedata

import com.example.pruebascompose.data.api.MoviesApi
import com.example.pruebascompose.data.remotedata.interceptors.AuthorizationInterceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    val okHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor())
   val retrofit = Retrofit.Builder()
       .baseUrl("https://api.themoviedb.org/3/")
       .client(okHttpClientBuilder.build())
       .addConverterFactory(GsonConverterFactory.create())
       .build()

    val apiService = retrofit.create(MoviesApi::class.java)
}