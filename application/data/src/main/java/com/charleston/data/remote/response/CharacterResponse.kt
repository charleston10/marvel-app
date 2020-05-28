package com.charleston.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("thumbnail") val thumbnail: ThumbnailResponse
) : Serializable