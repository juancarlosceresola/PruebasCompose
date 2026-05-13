package com.example.pruebascompose.data.repository

import com.example.pruebascompose.data.remotedata.AuthDataSource
import com.example.pruebascompose.domain.model.SessionBO
import com.example.pruebascompose.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun login(username: String, password: String): SessionBO {
        val sessionDto = authDataSource.login(username, password)
        return SessionBO(sessionId = sessionDto.sessionId)
    }
}
