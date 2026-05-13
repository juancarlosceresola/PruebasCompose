package com.example.pruebascompose.data.remotedata

import com.example.pruebascompose.core.extensions.parseResponse
import com.example.pruebascompose.data.api.MoviesApi
import com.example.pruebascompose.data.remotedata.dto.LoginRequestDto
import com.example.pruebascompose.data.remotedata.dto.SessionDto
import javax.inject.Inject

class AuthDataSource @Inject constructor(private val moviesApi: MoviesApi) {

    suspend fun login(username: String, password: String): SessionDto {
        val tokenResponse = moviesApi.getRequestToken().parseResponse()
        val validatedToken = moviesApi.validateWithLogin(
            LoginRequestDto(username, password, tokenResponse.requestToken)
        ).parseResponse()
        return moviesApi.createSession(mapOf("request_token" to validatedToken.requestToken)).parseResponse()
    }
}
