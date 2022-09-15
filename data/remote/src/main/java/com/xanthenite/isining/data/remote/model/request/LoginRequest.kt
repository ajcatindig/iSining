package com.xanthenite.isining.data.remote.model.request

import com.squareup.moshi.Json

data class LoginRequest(
        val email: String ,
        val password: String)
