package com.charleston.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ComicResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("thumbnail") val thumbnail: ThumbnailResponse
) : Serializable