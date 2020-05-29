package com.charleston.data.local

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ThemeEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val imageUrl: String
) : Serializable