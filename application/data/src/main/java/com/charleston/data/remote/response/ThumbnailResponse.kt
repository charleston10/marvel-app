package com.charleston.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ThumbnailResponse(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
) : Serializable