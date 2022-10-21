package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Exhibit

data class ExhibitsResponse(
        val count : Int,
        val data : List<Exhibit> = emptyList(),
        val page : Int,
        override val state : State ,
        override val message : String?,
) : BaseResponse

data class ExhibitResponse(
        override val state : State ,
        override val message : String?,
        val data : Exhibit
) : BaseResponse
