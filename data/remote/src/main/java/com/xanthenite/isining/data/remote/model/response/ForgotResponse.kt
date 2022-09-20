package com.xanthenite.isining.data.remote.model.response

data class ForgotResponse(
        override val state : State ,
        val hooray : String?,
        override val message : String?)
: BaseResponse