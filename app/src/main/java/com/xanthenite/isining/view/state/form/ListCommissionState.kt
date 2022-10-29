package com.xanthenite.isining.view.state.form

import com.xanthenite.isining.core.model.Commission
import com.xanthenite.isining.view.state.State

data class ListCommissionState(
        val isLoading : Boolean = false,
        val data : List<Commission> = emptyList(),
        val error: String? = null ,
        val isConnectivityAvailable: Boolean? = null
):State
