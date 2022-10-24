package com.xanthenite.isining.data.remote.model.request

data class CommissionRequest(
        val artist_user_id : Int,
        val price : Double,
        val description : String,
        val address : String)
