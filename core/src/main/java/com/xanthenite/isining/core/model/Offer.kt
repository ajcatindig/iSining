package com.xanthenite.isining.core.model

data class Offer(
        val id : Int,
        val price : Double,
        val note : String?,
        val address : String,
        val accepted_at : String?,
        val created_at : String?,
        val deleted_at : String?,
        val title : String
)
