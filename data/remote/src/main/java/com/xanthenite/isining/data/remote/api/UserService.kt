package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.request.ChangePassRequest
import com.xanthenite.isining.data.remote.model.request.UpdateProfileRequest
import com.xanthenite.isining.data.remote.model.response.ChangePassResponse
import com.xanthenite.isining.data.remote.model.response.UpdateProfileResponse
import com.xanthenite.isining.data.remote.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserService
{

    @Headers("Accept: application/json")
    @GET("api/user/current")
    suspend fun getUserProfile() : Response<UserResponse>

    @Headers("Accept: application/json")
    @POST("api/user")
    suspend fun updateProfile(@Body updateProfileRequest : UpdateProfileRequest) : Response<UpdateProfileResponse>

    @Headers("Accept: application/json")
    @POST("api/user/password-reset")
    suspend fun changePassword(@Body changePassRequest : ChangePassRequest) : Response<ChangePassResponse>

}