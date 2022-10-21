package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.core.model.Exhibit

data class ArtistsResponse(
        val count : Int ,
        val data : List<Artist> = emptyList() ,
        val page : Int ,
        override val state : State ,
        override val message : String?
) : BaseResponse

data class ArtistResponse(
        val data : Artist,
        override val state : State ,
        override val message : String?,
) : BaseResponse
