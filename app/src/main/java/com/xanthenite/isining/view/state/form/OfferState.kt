package com.xanthenite.isining.view.state.form

import com.xanthenite.isining.view.state.State

data class OfferState(
        val isLoading : Boolean = false,
        val isSuccess : String? = null,
        val error : String? = null,
        val artwork_id : Int = 0,
        val payment_channel_id : Int = 0,
        val price : String? = "",
        val address : String = "",
        val note : String? = "",
        val isValidAddress : Boolean? = null
) : State