package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.Artwork

data class ArtworksResponse(
        val data : List<Artwork> = emptyList(),
        override val state : State ,
        override val message : String?,
) : BaseResponse

data class ArtworkResponse(
        override val state : State ,
        override val message : String?,
        val data : Artwork
) : BaseResponse
