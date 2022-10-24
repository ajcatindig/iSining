package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.request.CommissionRequest
import com.xanthenite.isining.data.remote.model.request.OfferRequest
import com.xanthenite.isining.data.remote.model.response.CommissionResponse
import com.xanthenite.isining.data.remote.model.response.OfferResponse
import com.xanthenite.isining.data.remote.model.response.PaymentChannelsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransactionService
{

    @Headers("Accept: application/json")
    @GET("api/paymentChannels")
    suspend fun getPaymentChannels() : Response<PaymentChannelsResponse>

    @Headers("Accept: application/json")
    @POST("api/offers")
    suspend fun postOffer(@Body offerRequest : OfferRequest) : Response<OfferResponse>

    @Headers("Accept: application/json")
    @POST("api/commissions")
    suspend fun postCommission(@Body commissionRequest : CommissionRequest) : Response<CommissionResponse>

}