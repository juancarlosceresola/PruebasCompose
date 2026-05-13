package com.example.pruebascompose.domain.repository

import com.example.pruebascompose.domain.model.SessionBO

interface AuthRepository {
    suspend fun login(username: String, password: String): SessionBO
}
