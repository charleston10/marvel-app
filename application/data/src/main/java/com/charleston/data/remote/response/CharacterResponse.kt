package com.charleston.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterResponse(
    @SerializedName("name") val name: String
) : Serializable