package com.xanthenite.isining.view.state.main

import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.view.state.State

data class ArtistState(
        val isLoading: Boolean = false ,
        val data : List<Artist> = emptyList() ,
        val error: String? = null ,
        val isConnectivityAvailable: Boolean? = null
) : State