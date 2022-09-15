package com.xanthenite.isining.core.repository

/**
 * Result wrapper class which can have only two possible states i.e. Either success or failure
 */
sealed class Either<T> {
    data class Success<T>(val data: T) : Either<T>()
    data class Error<T>(val message: String) : Either<T>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> error(message: String) = Error<T>(message)
    }

    inline fun onSuccess(block: (T) -> Unit): Either<T> = apply {
        if (this is Success) {
            block(data)
        }
    }

    inline fun onFailure(block: (String) -> Unit): Either<T> = apply {
        if (this is Error) {
            block(message)
        }
    }
}
