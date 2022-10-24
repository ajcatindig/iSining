package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.PaymentChannel

data class PaymentChannelsResponse(
        val data : List<PaymentChannel> = emptyList(),
        override val state : State ,
        override val message : String?
): BaseResponse
