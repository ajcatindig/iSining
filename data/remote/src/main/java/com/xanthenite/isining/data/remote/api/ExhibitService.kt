package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.response.ExhibitResponse
import com.xanthenite.isining.data.remote.model.response.ExhibitsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ExhibitService
{

    @Headers("Accept: application/json")
    @GET("api/exhibits")
    suspend fun getCurrentExhibits() : Response<ExhibitsResponse>

    @Headers("Accept: application/json")
    @GET("api/exhibits/upcoming")
    suspend fun getUpcomingExhibits() : Response<ExhibitsResponse>

    @Headers("Accept: application/json")
    @GET("api/exhibits/past")
    suspend fun getPastExhibits() : Response<ExhibitsResponse>

    @Headers("Accept: application/json")
    @GET("api/exhibit/{id}")
    suspend fun getExhibitById(@Path("id") id : Int) : Response<ExhibitResponse>

}