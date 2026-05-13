package com.example.pruebascompose.data.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Locale

class FlexibleDateAdapter : JsonAdapter<Long>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    @FromJson
    override fun fromJson(reader: JsonReader): Long? {
        return when (reader.peek()) {
            JsonReader.Token.NUMBER -> reader.nextLong()
            JsonReader.Token.STRING -> {
                val dateString = reader.nextString()
                try { dateFormat.parse(dateString)?.time } catch (e: Exception) { null }
            }
            else -> { reader.skipValue(); null }
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Long?) {
        if (value != null) writer.value(value) else writer.nullValue()
    }
}
