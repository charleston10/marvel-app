package com.charleston.data.remote.api

import com.charleston.data.remote.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") page: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") order: String
    ): List<CharacterResponse>
}