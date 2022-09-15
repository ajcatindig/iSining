package com.xanthenite.isining.view.state

data class RegisterState(
        val isLoading : Boolean = false,
        val isLoggedIn : Boolean = false,
        val username : String = "",
        val email : String = "",
        val password : String = "",
        val confirmPassword : String = "",
        val isValidUsername : Boolean? = null,
        val isValidEmail : Boolean? = null,
        val isValidPassword : Boolean? = null,
        val isValidConfirmPassword : Boolean? = null,
        val error : String? = null) : State
