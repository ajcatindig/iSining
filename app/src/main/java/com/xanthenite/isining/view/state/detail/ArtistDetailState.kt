package com.xanthenite.isining.view.state.detail

import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.view.state.State

data class ArtistDetailState(
        val isLoading: Boolean = false ,
        val data : Artist ,
        val error: String? = null ,
        val isConnectivityAvailable: Boolean? = null ,
) : State