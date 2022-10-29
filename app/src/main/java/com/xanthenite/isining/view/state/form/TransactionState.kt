package com.xanthenite.isining.view.state.form

import com.xanthenite.isining.core.model.Transaction
import com.xanthenite.isining.view.state.State

data class TransactionState(
        val isLoading: Boolean = false ,
        val data : List<Transaction> = emptyList() ,
        val error: String? = null ,
        val isConnectivityAvailable: Boolean? = null
) : State
