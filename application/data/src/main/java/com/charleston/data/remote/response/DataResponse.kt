package com.charleston.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataResponse(
    @SerializedName("total") val id: Int,
    @SerializedName("results") val characters: List<CharacterResponse>
) : Serializable