package com.example.pruebascompose.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.pruebascompose.domain.model.MovieBO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val MovieNavType = object : NavType<MovieBO>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): MovieBO? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }
    override fun parseValue(value: String): MovieBO {
        return Json.decodeFromString(Uri.decode(value))
    }
    override fun serializeAsValue(value: MovieBO): String {
        return Uri.encode(Json.encodeToString(value))
    }
    override fun put(bundle: Bundle, key: String, value: MovieBO) {
        bundle.putString(key, Json.encodeToString(value))
    }
}