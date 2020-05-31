package com.charleston.data.remote.response

import com.squareup.moshi.Json

data class ThumbnailResponse(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)