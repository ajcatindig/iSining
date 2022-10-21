package com.xanthenite.isining.view.state.main

import com.xanthenite.isining.core.model.Exhibit
import com.xanthenite.isining.view.state.State

data class ExhibitState(
        val isLoading: Boolean = false,
        val data : List<Exhibit> = emptyList(),
        val error: String? = null,
        val isConnectivityAvailable: Boolean? = null
) : State
