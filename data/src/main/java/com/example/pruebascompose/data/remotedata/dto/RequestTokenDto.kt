package com.example.pruebascompose.data.remotedata.dto

import com.squareup.moshi.Json

data class RequestTokenDto(
    @Json(name = "success") val success: Boolean,
    @Json(name = "expires_at") val expiresAt: String,
    @Json(name = "request_token") val requestToken: String
)
