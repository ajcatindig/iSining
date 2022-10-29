package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Offer

data class ListOfferResponse(
        val data : List<Offer> = emptyList(),
        override val state : State ,
        override val message : String?
): BaseResponse
