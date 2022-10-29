package com.xanthenite.isining.core.model

data class Commission(
        val id : Int,
        val price : Double,
        val description : String,
        val address : String,
        val accepted_at : String?,
        val created_at : String?,
        val deleted_at : String?,
        val artist_name : String
)
