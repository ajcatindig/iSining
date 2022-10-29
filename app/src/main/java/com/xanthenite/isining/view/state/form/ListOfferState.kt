package com.xanthenite.isining.view.state.form

import com.xanthenite.isining.core.model.Offer
import com.xanthenite.isining.view.state.State

data class ListOfferState(
        val isLoading : Boolean = false,
        val data : List<Offer> = emptyList(),
        val error: String? = null ,
        val isConnectivityAvailable: Boolean? = null
) : State
