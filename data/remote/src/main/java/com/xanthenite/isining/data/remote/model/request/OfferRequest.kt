package com.xanthenite.isining.data.remote.model.request

data class OfferRequest(
        val artwork_id : Int,
        val price : Double?,
        val payment_channel_id : Int,
        val note : String?,
        val address : String)
