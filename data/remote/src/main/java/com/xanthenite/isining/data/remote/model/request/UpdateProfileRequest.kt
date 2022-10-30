package com.xanthenite.isining.data.remote.model.request

data class UpdateProfileRequest(
        val mobile_number : String,
        val address : String,
        val bio : String,
        val profile_photo_path : String,
        val name : String,)
