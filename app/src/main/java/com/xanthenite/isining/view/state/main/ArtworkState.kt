package com.xanthenite.isining.view.state.main

import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.view.state.State

data class ArtworkState(
        val isLoading: Boolean = false,
        val data : List<Artwork> = emptyList(),
        val error: String? = null,
        val isConnectivityAvailable: Boolean? = null
) : State