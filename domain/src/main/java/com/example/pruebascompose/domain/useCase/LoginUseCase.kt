package com.example.pruebascompose.domain.useCase

import com.example.pruebascompose.domain.base.UseCase
import com.example.pruebascompose.domain.model.SessionBO
import com.example.pruebascompose.domain.repository.AuthRepository
import javax.inject.Inject

data class LoginInput(val username: String, val password: String)

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<LoginInput, SessionBO>() {
    override suspend fun useCaseFunction(input: LoginInput): SessionBO =
        authRepository.login(input.username, input.password)
}
