package com.xanthenite.isining.repository

import com.xanthenite.isining.core.model.AuthCredential
import com.xanthenite.isining.core.repository.AuthRepository
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.data.remote.api.AuthService
import com.xanthenite.isining.data.remote.model.request.LoginRequest
import com.xanthenite.isining.data.remote.model.response.State
import com.xanthenite.isining.data.remote.util.getResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single source of data of User of the app.
 */
@Singleton
class AuthRepositoryImpl @Inject internal constructor(
        private val authService : AuthService
        ) : AuthRepository
{
    override suspend fun addUser(
            username : String , email : String , password : String
                                ) : Either<AuthCredential>
    {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByEmailAndPassword(
            email : String , password : String) : Either<AuthCredential>
    {
        return runCatching {
            val authResponse = authService.login(LoginRequest(email, password)).getResponse()

            when (authResponse.state) {
                State.SUCCESS -> Either.success(AuthCredential(authResponse.token!!))
                else -> Either.error(authResponse.message!!)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }
}