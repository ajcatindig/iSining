package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Featured

data class FeaturedResponse(
        override val state : State ,
        override val message : String?,
        val data : Featured?
) : BaseResponse
