package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Artist

data class ArtistsResponse(
        val count : Int ,
        val data : List<Artist> = emptyList() ,
        val page : Int ,
        override val state : State ,
        override val message : String?
) : BaseResponse
