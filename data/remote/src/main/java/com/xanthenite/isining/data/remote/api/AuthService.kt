package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.request.LoginRequest
import com.xanthenite.isining.data.remote.model.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService
{

    @Headers("Accept: application/json")
    @POST("api/sanctum/token")
    suspend fun login(@Body authRequest: LoginRequest): Response<AuthResponse>

}