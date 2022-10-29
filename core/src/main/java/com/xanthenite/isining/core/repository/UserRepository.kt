package com.xanthenite.isining.core.repository

import com.xanthenite.isining.core.model.ChangePassResult
import com.xanthenite.isining.core.model.ManageProfileResult
import com.xanthenite.isining.core.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File
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

    /**
     * Updates current user profile.
     */
    suspend fun updateProfile(
            name : String ,
            mobile_number : String ,
            address : String ,
            bio : String
    ) : Either<ManageProfileResult>

    /**
     * Updates current password.
     */
    suspend fun changePassword(
            password : String,
            password_new : String,
            password_confirmation : String
    ) : Either<ChangePassResult>
}