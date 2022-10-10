package com.xanthenite.isining.view.state.main

import com.xanthenite.isining.core.model.User
import com.xanthenite.isining.view.state.State

data class ProfileState(
        val isLoading: Boolean = false,
        val data : User,
        val error: String? = null,
        val isConnectivityAvailable: Boolean? = null,
        val isUserLoggedIn : Boolean? = null
) : State