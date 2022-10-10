package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserService
{

    @Headers("Accept: application/json")
    @GET("api/user/current")
    suspend fun getUserProfile() : Response<UserResponse>

}