package com.xanthenite.isining.data.remote.model.response

interface BaseResponse {
    val state: State
    val message: String?
}

enum class State {
    SUCCESS, NOT_FOUND, FAILED, UNAUTHORIZED
}