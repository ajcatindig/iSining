package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.response.ExhibitsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ExhibitService
{

    @Headers("Accept: application/json")
    @GET("api/exhibits")
    suspend fun getAllExhibits() : Response<ExhibitsResponse>

    @Headers("Accept: application/json")
    @GET("api/exhibits/{id}")
    suspend fun getExhibitById() : Response<ExhibitsResponse>

}