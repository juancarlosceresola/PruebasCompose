package com.example.pruebascompose.data.remotedata.dto

import com.squareup.moshi.Json

data class LoginRequestDto(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
    @Json(name = "request_token") val requestToken: String
)
