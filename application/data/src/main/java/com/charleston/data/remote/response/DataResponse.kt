package com.charleston.data.remote.response

import com.squareup.moshi.Json

data class DataResponse<T>(
    @Json(name = "total") val total: Int,
    @Json(name = "results") val results: List<T>
)