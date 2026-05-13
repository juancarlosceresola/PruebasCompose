package com.example.pruebascompose.data.remotedata.dto

import com.squareup.moshi.Json

data class SessionDto(
    @Json(name = "success") val success: Boolean,
    @Json(name = "session_id") val sessionId: String
)
