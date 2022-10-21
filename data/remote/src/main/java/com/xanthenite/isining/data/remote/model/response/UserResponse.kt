package com.xanthenite.isining.data.remote.model.response

import com.xanthenite.isining.core.model.User

data class UserResponse(
        val data : User ,
        override val state : State ,
        override val message : String?
) : BaseResponse
