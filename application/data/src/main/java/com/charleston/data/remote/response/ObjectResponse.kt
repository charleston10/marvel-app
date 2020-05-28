package com.charleston.data.remote.response

import com.squareup.moshi.Json

data class ObjectResponse(
    @Json(name = "data") val data: DataResponse
)