package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.response.ArtistsResponse
import com.xanthenite.isining.data.remote.model.response.ArtworksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ArtistService
{

    @Headers("Accept: application/json")
    @GET("api/users/artists")
    suspend fun getAllArtists() : Response<ArtistsResponse>

    @Headers("Accept: application/json")
    @GET("api/users/artists/{id}")
    suspend fun getArtistById() : Response<ArtistsResponse>

}