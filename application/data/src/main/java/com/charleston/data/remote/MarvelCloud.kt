package com.charleston.data.remote

import com.charleston.data.remote.request.MarvelApi
import com.charleston.data.remote.response.ObjectResponse

class MarvelCloud(
    private val api: MarvelApi
) {

    suspend fun getCharacters(page: Int, limit: Int): ObjectResponse {
        return api.getCharacters(
            page = page,
            limit = limit,
            order = "name"
        )
    }
}