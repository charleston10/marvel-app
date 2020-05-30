package com.charleston.data.remote

import com.charleston.data.remote.request.MarvelApi
import com.charleston.data.remote.response.CharacterResponse
import com.charleston.data.remote.response.ObjectResponse
import com.charleston.data.remote.response.SerieResponse

class MarvelCloud(
    private val api: MarvelApi
) {

    suspend fun getCharacters(offset: Int, limit: Int, queryName: String?): ObjectResponse<CharacterResponse> {
        var options: Map<String, String> = mapOf()

        if (queryName != null && queryName.isNotBlank()) {
            options = mapOf("nameStartsWith" to queryName)
        }

        return api.getCharacters(
            offset = offset,
            limit = limit,
            order = "name",
            options = options
        )
    }

    suspend fun getSeries(offset: Int, limit: Int, queryName: String?): ObjectResponse<SerieResponse> {
        var options: Map<String, String> = mapOf()

        if (queryName != null && queryName.isNotBlank()) {
            options = mapOf("nameStartsWith" to queryName)
        }

        return api.getSeries(
            offset = offset,
            limit = limit,
            options = options
        )
    }
}