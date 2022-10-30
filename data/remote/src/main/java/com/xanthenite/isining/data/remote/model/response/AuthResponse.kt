package com.xanthenite.isining.data.remote.model.response

data class AuthResponse(
        override val state: State ,
        override val message: String? ,
        val token: String?
) : BaseResponse
