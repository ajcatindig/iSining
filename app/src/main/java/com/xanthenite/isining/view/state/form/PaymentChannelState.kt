package com.xanthenite.isining.view.state.form

import com.xanthenite.isining.core.model.PaymentChannel
import com.xanthenite.isining.view.state.State

data class PaymentChannelState(
        val isLoading: Boolean = false ,
        val data : List<PaymentChannel> = emptyList(),
        val error: String? = null,
        val isConnectivityAvailable: Boolean? = null
) : State
