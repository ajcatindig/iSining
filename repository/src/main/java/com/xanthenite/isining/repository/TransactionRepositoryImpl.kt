package com.xanthenite.isining.repository

import android.util.Log
import com.xanthenite.isining.core.model.HireArtistResult
import com.xanthenite.isining.core.model.OfferResult
import com.xanthenite.isining.core.model.PaymentChannel
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.core.repository.TransactionRepository
import com.xanthenite.isining.data.remote.api.TransactionService
import com.xanthenite.isining.data.remote.model.request.CommissionRequest
import com.xanthenite.isining.data.remote.model.request.OfferRequest
import com.xanthenite.isining.data.remote.model.response.State
import com.xanthenite.isining.data.remote.util.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepositoryImpl @Inject internal constructor(
    private val transactionService : TransactionService
) : TransactionRepository
{
    override fun getPaymentChannels() : Flow<Either<List<PaymentChannel>>> = flow {
        val paymentChannelsResponse = transactionService.getPaymentChannels().getResponse()

        val state = when(paymentChannelsResponse.state) {
            State.SUCCESS -> Either.success(paymentChannelsResponse.data)
            else -> Either.error(paymentChannelsResponse.message!!)
        }
        emit(state)
        Log.d("PaymentChannelsData", "${paymentChannelsResponse.data}")
    }.catch {
        Log.e("TransactionRepoImpl", "catch ${it.message!!}")
        emit(Either.error("An unknown error occurred"))
    }

    override suspend fun postOffer(
            artwork_id : Int ,
            price : Double? ,
            payment_channel_id : Int ,
            note : String?,
            address : String
    ) : Either<OfferResult>
    {
        return runCatching {
            val offerResponse = transactionService.postOffer(
                OfferRequest(
                        artwork_id,
                        price,
                        payment_channel_id,
                        note,
                        address))
                .getResponse()

            when(offerResponse.state) {
                State.SUCCESS -> Either.success(OfferResult(offerResponse.message.toString()))
                else -> Either.error(offerResponse.message!!)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }

    override suspend fun postCommission(
            artist_user_id : Int ,
            price : Double ,
            description : String ,
            address : String
    ) : Either<HireArtistResult>
    {
        return runCatching {
            val commissionResponse = transactionService.postCommission(
                    CommissionRequest(
                            artist_user_id,
                            price,
                            description,
                            address))
                    .getResponse()

            when(commissionResponse.state) {
                State.SUCCESS -> Either.success(HireArtistResult(commissionResponse.message.toString()))
                else -> Either.error(commissionResponse.message!!)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }
}