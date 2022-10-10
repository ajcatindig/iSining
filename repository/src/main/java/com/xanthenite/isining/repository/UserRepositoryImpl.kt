package com.xanthenite.isining.repository

import android.util.Log
import com.xanthenite.isining.core.model.User
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.core.repository.UserRepository
import com.xanthenite.isining.data.remote.api.UserService
import com.xanthenite.isining.data.remote.model.response.State
import com.xanthenite.isining.data.remote.util.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject internal constructor(
        private val userService : UserService
) : UserRepository
{
    override fun getCurrentUser() : Flow<Either<User>> = flow {
        val userResponse = userService.getUserProfile().getResponse()

        val state = when (userResponse.state) {
            State.SUCCESS -> Either.success(userResponse.data)
            else -> Either.error(userResponse.message!!)
        }
        emit(state)
        Log.d("Data" , "${userResponse.data}")
    }.catch {
        Log.e("UserRepositoryImpl" , "catch ${it.message!!}")
        emit(Either.error("An unknown error occurred"))
    }
}