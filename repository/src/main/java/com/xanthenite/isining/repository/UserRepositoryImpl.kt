package com.xanthenite.isining.repository

import android.util.Log
import com.xanthenite.isining.core.model.ChangePassResult
import com.xanthenite.isining.core.model.ManageProfileResult
import com.xanthenite.isining.core.model.User
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.core.repository.UserRepository
import com.xanthenite.isining.data.remote.api.UserService
import com.xanthenite.isining.data.remote.model.request.ChangePassRequest
import com.xanthenite.isining.data.remote.model.request.UpdateProfileRequest
import com.xanthenite.isining.data.remote.model.response.State
import com.xanthenite.isining.data.remote.util.getResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.File
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

    override suspend fun updateProfile(
            name : String ,
            mobile_number : String ,
            address : String ,
            bio : String
    ) : Either<ManageProfileResult>
    {
        return runCatching {
            val updateProfileResponse = userService.updateProfile(
                    UpdateProfileRequest(
                            name,
                            mobile_number,
                            address,
                            bio))
                    .getResponse()

            when(updateProfileResponse.state) {
                State.SUCCESS -> Either.success(ManageProfileResult(updateProfileResponse.message.toString()))
                else -> Either.error(updateProfileResponse.message!!)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }

    override suspend fun changePassword(
            password : String ,
            password_new : String ,
            password_confirmation : String
    ) : Either<ChangePassResult>
    {
        return runCatching {
            val changePassResponse = userService.changePassword(
                    ChangePassRequest(
                            password,
                            password_new,
                            password_confirmation))
                    .getResponse()

            when (changePassResponse.state) {
                State.SUCCESS -> Either.success(ChangePassResult(changePassResponse.message.toString()))
                else -> Either.error(changePassResponse.message!!)
            }
        }.getOrDefault(Either.error("Something went wrong!"))
    }
}