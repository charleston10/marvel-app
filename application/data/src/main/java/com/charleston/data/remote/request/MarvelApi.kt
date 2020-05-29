package com.charleston.data.remote.request

import com.charleston.data.remote.response.ObjectResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") order: String
    ): ObjectResponse
}