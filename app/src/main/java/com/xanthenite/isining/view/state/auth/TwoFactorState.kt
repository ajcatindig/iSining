package com.xanthenite.isining.view.state.auth

import com.xanthenite.isining.view.state.State

data class TwoFactorState(
        val isLoading: Boolean = false,
        val isLoggedIn: Boolean = false,
        val error: String? = null,
        val verification_code : String
) : State
