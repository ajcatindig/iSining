package com.xanthenite.isining.data.remote.api

import com.xanthenite.isining.data.remote.model.request.CommissionRequest
import com.xanthenite.isining.data.remote.model.request.OfferRequest
import com.xanthenite.isining.data.remote.model.response.*
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
    @GET("api/offers")
    suspend fun getOffers() : Response<ListOfferResponse>

    @Headers("Accept: application/json")
    @POST("api/commissions")
    suspend fun postCommission(@Body commissionRequest : CommissionRequest) : Response<CommissionResponse>

    @Headers("Accept: application/json")
    @GET("api/commissions")
    suspend fun getCommissions() : Response<ListCommissionResponse>

    @Headers("Accept: application/json")
    @GET("api/transactions")
    suspend fun getTransactions() : Response<TransactionResponse>

}