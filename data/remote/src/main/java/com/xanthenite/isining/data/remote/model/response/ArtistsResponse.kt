package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.core.model.Exhibit

data class ArtistsResponse(
        val data : List<Artist> = emptyList() ,
        override val state : State ,
        override val message : String?
) : BaseResponse

data class ArtistResponse(
        val data : Artist,
        override val state : State ,
        override val message : String?,
) : BaseResponse
