package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.*
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

    /**
     * Returns list of transactions.
     */
    fun getTransactions() : Flow<Either<List<Transaction>>>

    /**
     * Returns list of pending offers
     */
    fun getOffers() : Flow<Either<List<Offer>>>

    /**
     * Returns list of pending commissions
     */
    fun getCommissions() : Flow<Either<List<Commission>>>
}