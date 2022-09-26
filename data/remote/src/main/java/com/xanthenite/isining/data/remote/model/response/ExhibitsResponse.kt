package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Exhibit

data class ExhibitsResponse(
        override val state : State ,
        override val message : String?,
        val data : List<Exhibit> = emptyList()
) : BaseResponse

data class ExhibitResponse(
        override val state : State ,
        override val message : String?,
        val id : Int?
) : BaseResponse
