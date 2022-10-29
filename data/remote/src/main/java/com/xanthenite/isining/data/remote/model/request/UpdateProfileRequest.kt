package com.xanthenite.isining.data.remote.model.request

data class UpdateProfileRequest(
        val name : String,
        val mobile_number : String,
        val address : String,
        val bio : String)
