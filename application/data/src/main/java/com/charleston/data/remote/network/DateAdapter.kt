package com.charleston.data.remote.network

import com.google.gson.JsonParseException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter : JsonAdapter<Date>() {

    private val dateFormat: DateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("pt", "BR"))
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
    }

    override fun fromJson(reader: JsonReader): Date? {
        try {
            val dateAsString = reader.nextString()
            return dateFormat.parse(dateAsString)
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
    }

    override fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }
}