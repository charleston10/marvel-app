package com.charleston.data.remote.response

import com.squareup.moshi.Json

data class ObjectResponse<T>(
    @Json(name = "data") val data: DataResponse<T>
)