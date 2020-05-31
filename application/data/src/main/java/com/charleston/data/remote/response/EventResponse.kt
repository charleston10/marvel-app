package com.charleston.data.remote.response

import com.squareup.moshi.Json
import java.util.*

data class EventResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?,
    @Json(name = "thumbnail") val thumbnail: ThumbnailResponse,
    @Json(name = "start") val startDate: Date,
    @Json(name = "end") val endDate: Date
)