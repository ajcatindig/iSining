package com.xanthenite.isining.view.state.main

import com.xanthenite.isining.core.model.Featured
import com.xanthenite.isining.view.state.State

data class HomeState(
        val isLoading: Boolean = false,
        val data : Featured,
        val error: String? = null,
        val isConnectivityAvailable: Boolean? = null
) : State
