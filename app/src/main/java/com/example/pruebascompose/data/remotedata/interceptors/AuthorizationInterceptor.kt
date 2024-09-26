package com.example.pruebascompose.data.remotedata.interceptors

import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named


class AuthorizationInterceptor @Inject constructor()
    : Interceptor {


        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val newRequest = originalRequest.newBuilder()
                .header("Authorization", BEARER)
                .header("language","es-ES")
                .build()

            return chain.proceed(newRequest)
        }
    companion object {
        private const val BEARER = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5Y2M2MDgyZmFhYzFhZmM1MzNkOGU0NGRkMmQzZWY3MyIsIm5iZiI6MTcyNjU5MzQxMC40MTEzMzUsInN1YiI6IjYxMTEwYWM0Mzg3NjUxMDA2MDRiZDVjNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.skXz7S0Zb7kAOFQuxZw4njZ0r24GWPQwB-IqawGPxe8"

    }
}