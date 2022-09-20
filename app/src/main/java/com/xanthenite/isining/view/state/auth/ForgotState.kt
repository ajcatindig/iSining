package com.xanthenite.isining.view.state.auth

import com.xanthenite.isining.view.state.State

data class ForgotState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val email: String = "",
        val isSuccess : String? = null,
        val isValidEmail : Boolean? = null
) : State
