package com.xanthenite.isining.core.model

data class Transaction(
        val id : Int,
        val title : String,
        val price : Double,
        val address : String,
        val description : String? /*or note*/,
        val verified_at : String?,
        val created_at : String,
)
