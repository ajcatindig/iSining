package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.response.ArtworkResponse
import com.xanthenite.isining.data.remote.model.response.ArtworksResponse
import com.xanthenite.isining.data.remote.model.response.ExhibitsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ArtworkService
{

    @Headers("Accept: application/json")
    @GET("api/artworks")
    suspend fun getAllArtworks() : Response<ArtworksResponse>

    @Headers("Accept: application/json")
    @GET("api/artwork/{id}")
    suspend fun getArtworkById(@Path("id") id : Int) : Response<ArtworkResponse>

}