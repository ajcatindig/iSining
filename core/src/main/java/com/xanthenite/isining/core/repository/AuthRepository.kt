package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.AuthCredential
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
    suspend fun addUser(username: String, email : String, password: String): Either<AuthCredential>

    /**
     * Sign ins a user using [email] and [password] which is already exists.
     */
    suspend fun getUserByEmailAndPassword(
            email: String,
            password: String)
    : Either<AuthCredential>
}