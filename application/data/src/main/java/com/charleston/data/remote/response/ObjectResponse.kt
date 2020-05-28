package com.charleston.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ObjectResponse(
    @SerializedName("copyright") val copyright: String,
    @SerializedName("data") val data: DataResponse
) : Serializable