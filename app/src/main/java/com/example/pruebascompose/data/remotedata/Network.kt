package com.example.pruebascompose.data.remotedata

import com.example.pruebascompose.data.api.MoviesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
   val retrofit = Retrofit.Builder()
       .baseUrl("https://api.themoviedb.org/3/")
       .addConverterFactory(GsonConverterFactory.create())
       .build()

    val apiService = retrofit.create(MoviesApi::class.java)
}