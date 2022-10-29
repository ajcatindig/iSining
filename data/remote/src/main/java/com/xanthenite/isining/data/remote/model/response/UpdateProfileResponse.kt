package com.xanthenite.isining.data.remote.model.response

data class UpdateProfileResponse(
        override val state : State ,
        override val message : String?
) : BaseResponse
