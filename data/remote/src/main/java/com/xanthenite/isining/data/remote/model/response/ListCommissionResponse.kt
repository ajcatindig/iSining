package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Commission

data class ListCommissionResponse(
        val data : List<Commission> = emptyList() ,
        override val state : State ,
        override val message : String?) : BaseResponse
