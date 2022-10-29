package com.xanthenite.isining.view.state.form

import com.xanthenite.isining.view.state.State

data class ChangePassState(
        val isLoading : Boolean = false,
        val isSuccess : String? = null,
        val error : String? = null,
        val currentPassword : String = "",
        val newPassword : String = "",
        val confirmPassword : String = "",
        val isValidNewPass : Boolean? = null,
        val isValidConfirmPass : Boolean? = null
) : State
