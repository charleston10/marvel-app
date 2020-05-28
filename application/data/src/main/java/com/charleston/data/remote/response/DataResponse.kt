package com.charleston.data.remote.response

import com.squareup.moshi.Json

data class DataResponse(
    @Json(name = "total") val total: Int,
    @Json(name = "results") val characters: List<CharacterResponse>
)