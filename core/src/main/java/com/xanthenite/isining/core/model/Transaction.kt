package com.xanthenite.isining.core.model

data class Transaction(
        val id : Int,
        val payment_channel_id : Int,
        val amount : Double,
        val verified_at : String?,
        val created_at : String,
        val title : String,
        val address : String,
        val note : String? /*or note*/,
)
