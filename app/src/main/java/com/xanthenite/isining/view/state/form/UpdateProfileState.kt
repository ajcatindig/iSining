package com.xanthenite.isining.view.state.form

import com.xanthenite.isining.view.state.State

data class UpdateProfileState(
        val isLoading : Boolean = false ,
        val isSuccess : String? = null ,
        val error : String? = null ,
        val name : String = "" ,
        val mobile_number : String = "" ,
        val address : String = "" ,
        val bio : String = "" ,
        val picture : String = "",
        val isValidUsername : Boolean? = null ,
        val isValidNumber : Boolean? = null ,
        val isValidAddress : Boolean? = null
) : State
