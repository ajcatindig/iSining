package com.xanthenite.isining.data.remote.model.response

import com.squareup.moshi.Json

data class AuthResponse(
        override val state: State ,
        override val message: String? ,
        val token: String?) : BaseResponse
