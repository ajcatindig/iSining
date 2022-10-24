package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.Exhibit
import com.xanthenite.isining.core.model.HireArtistResult
import com.xanthenite.isining.core.model.OfferResult
import com.xanthenite.isining.core.model.PaymentChannel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * Repository for Transactions.
 */
@Singleton
interface TransactionRepository
{
    /**
     * Returns modes of payments.
     */
    fun getPaymentChannels() : Flow<Either<List<PaymentChannel>>>

    /**
     * Posts an offer for a specific artwork
     */
    suspend fun postOffer(
            artwork_id : Int,
            price : Double?,
            payment_channel_id : Int,
            note : String?,
            address : String
    ) : Either<OfferResult>

    /**
     * Posts an offer for a specific artist
     */
    suspend fun postCommission(
            artist_user_id : Int,
            price : Double,
            description : String,
            address : String
    ) : Either<HireArtistResult>
}