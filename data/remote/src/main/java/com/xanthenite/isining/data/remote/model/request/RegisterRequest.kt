package com.xanthenite.isining.data.remote.model.request

data class RegisterRequest(
        val name : String,
        val email: String ,
        val password: String,
        val password_confirmation : String)
