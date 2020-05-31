package com.charleston.data.remote.request

import com.charleston.data.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") order: String,
        @QueryMap options: Map<String, String>
    ): ObjectResponse<CharacterResponse>

    @GET("/v1/public/series")
    suspend fun getSeries(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ObjectResponse<SerieResponse>

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ObjectResponse<ComicResponse>

    @GET("/v1/public/events")
    suspend fun getEvents(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") order: String
    ): ObjectResponse<EventResponse>
}