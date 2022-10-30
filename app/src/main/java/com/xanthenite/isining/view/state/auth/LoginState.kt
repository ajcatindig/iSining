package com.xanthenite.isining.view.state.auth

import com.xanthenite.isining.view.state.State

data class LoginState(
        val isLoading: Boolean = false,
        val isLoggedIn: Boolean = false,
        val isSuccess : Boolean = false,
        val error: String? = null,
        val email: String = "",
        val password: String = ""
) : State
