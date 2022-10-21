package com.xanthenite.isining.view.state.detail

import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.view.state.State

data class ArtworkDetailState(
        val isLoading: Boolean = false ,
        val data : Artwork ,
        val error: String? = null ,
        val isConnectivityAvailable: Boolean? = null ,
) : State
