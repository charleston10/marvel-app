package com.charleston.data.remote.response

import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "thumbnail") val thumbnail: ThumbnailResponse
)