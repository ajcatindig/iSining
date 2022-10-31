package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Exhibit

data class ExhibitsResponse(
        val data : List<Exhibit> = emptyList(),
        override val state : State ,
        override val message : String?,
) : BaseResponse

data class ExhibitResponse(
        override val state : State ,
        override val message : String?,
        val data : Exhibit
) : BaseResponse
