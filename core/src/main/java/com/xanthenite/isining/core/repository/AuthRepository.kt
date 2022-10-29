package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.AuthCredential
import com.xanthenite.isining.core.model.ForgotResult
import com.xanthenite.isining.core.model.RegisterResult
import javax.inject.Singleton

/**
 * Network Repository for user authorization of noty.
 */
@Singleton
interface AuthRepository
{
    /**
     * Register/Create a new user using [username] and [password]
     */
    suspend fun addUser(
            username: String,
            email : String,
            password: String,
            password_confirmation : String)
    : Either<RegisterResult>

    /**
     * Sign ins a user using [email] and [password] which is already exists.
     */
    suspend fun getUserByEmailAndPassword(
            email: String,
            password: String)
    : Either<AuthCredential>

    /**
     *  Forgot Password
     */
    suspend fun forgotPassword(email : String) : Either<ForgotResult>

    /**
     * Two Factor Authentication
     */
    suspend fun authenticate(verification_code : String) : Either<AuthCredential>
}