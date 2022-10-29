package com.xanthenite.isining.data.remote.model.request

data class ChangePassRequest(
        val password : String,
        val password_new : String,
        val password_confirmation : String)