package com.xanthenite.isining.core.model

data class User(
        val id : Int?,
        val name : String?,
        val first_name : String?,
        val last_name : String?,
        val birthdate : String?,
        val type : String?,
        val gender : String?,
        val mobile_number : String?,
        val bio : String?,
        val email : String?,
        val profile_photo_path : String?,
        val address : String?
)
