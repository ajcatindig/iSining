package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * Repository for Current User.
 */
@Singleton
interface UserRepository
{
    /**
     * Returns current user.
     */
    fun getCurrentUser() : Flow<Either<User>>
}