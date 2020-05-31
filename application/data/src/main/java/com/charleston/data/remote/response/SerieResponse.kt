package com.charleston.data.remote.response

import com.squareup.moshi.Json

data class SerieResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?,
    @Json(name = "thumbnail") val thumbnail: ThumbnailResponse
)