package com.xanthenite.isining.view.state.detail

import com.xanthenite.isining.core.model.Exhibit
import com.xanthenite.isining.view.state.State

data class ExhibitDetailState(
        val isLoading: Boolean = false ,
        val data : Exhibit ,
        val error: String? = null ,
        val isConnectivityAvailable: Boolean? = null ,
) : State