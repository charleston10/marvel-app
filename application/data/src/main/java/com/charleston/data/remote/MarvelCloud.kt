package com.charleston.data.remote

import com.charleston.data.remote.request.MarvelApi
import com.charleston.data.remote.response.*

class MarvelCloud(
    private val api: MarvelApi
) {

    suspend fun getCharacters(
        offset: Int,
        limit: Int,
        queryName: String?
    ): ObjectResponse<CharacterResponse> {
        var options: Map<String, String> = mapOf()

        if (queryName != null && queryName.isNotBlank()) {
            options = mapOf("name" to queryName)
        }

        return api.getCharacters(
            offset = offset,
            limit = limit,
            order = "name",
            options = options
        )
    }

    suspend fun getSeries(
        offset: Int,
        limit: Int
    ): ObjectResponse<SerieResponse> {
        return api.getSeries(
            offset = offset,
            limit = limit
        )
    }

    suspend fun getComics(
        offset: Int,
        limit: Int
    ): ObjectResponse<ComicResponse> {
        return api.getComics(
            offset = offset,
            limit = limit
        )
    }

    suspend fun getEvents(): ObjectResponse<EventResponse> {
        return api.getEvents(
            offset = 0,
            limit = 30,
            order = "-startDate"
        )
    }
}