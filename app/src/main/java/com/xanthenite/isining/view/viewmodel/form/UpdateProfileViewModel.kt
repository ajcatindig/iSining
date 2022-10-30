package com.xanthenite.isining.view.viewmodel.form

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.UserRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.utils.validator.UserValidator
import com.xanthenite.isining.view.state.form.UpdateProfileState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
        @RemoteRepository val userRepository : UserRepository
) : BaseViewModel<UpdateProfileState>(initialState = UpdateProfileState())
{
    init {
        loadUser()
    }
    fun setName(name : String) {
        setState { state -> state.copy(name = name) }
    }

    fun setMobileNumber(mobile_number : String) {
        setState { state -> state.copy(mobile_number = mobile_number) }
    }

    fun setAddress(address : String) {
        setState { state -> state.copy(address = address) }
    }

    fun setBio(bio : String) {
        setState { state -> state.copy(bio = bio) }
    }

    fun setPicture(picture : String) {
        setState { state -> state.copy(picture = picture) }
    }

    fun loadUser()
    {
        userRepository.getCurrentUser()
                .distinctUntilChanged()
                .onEach { response ->
                    response.onSuccess { data ->
                        setState { state -> state.copy(
                                isLoading = false,
                                name = data.name.orEmpty(),
                                mobile_number = data.mobile_number.orEmpty(),
                                address = data.address.orEmpty(),
                                bio = data.bio.orEmpty(),
                                picture = data.profile_photo_path.orEmpty()) }
                    }.onFailure { message ->
                        setState { state -> state.copy(isLoading = false, error = message) }
                    }
                }.onStart { setState { state -> state.copy(isLoading = true) } }
                .launchIn(viewModelScope)
    }


    fun updateProfile() {
        if (!validateInfo()) return

        viewModelScope.launch {
            val name = currentState.name
            val mobile_number = currentState.mobile_number
            val address = currentState.address
            val bio = currentState.bio
            val profile_photo_path = currentState.picture

            setState { state -> state.copy(isLoading = true) }

            val response = userRepository.updateProfile(mobile_number, address, bio, profile_photo_path, name)

            response.onSuccess { message ->
                setState { state ->
                    state.copy(isLoading = false,
                               isSuccess = message.message,
                               error = null,
                               name = name,
                               mobile_number = mobile_number,
                               address = address,
                               bio = bio)
                }
            }.onFailure { message ->
                setState { state ->
                    state.copy(isLoading = false,
                               isSuccess = null,
                               error = message)
                }
            }
        }
    }


    private fun validateInfo() : Boolean {
        val username = currentState.name
        val mobile_number = currentState.mobile_number
        val address = currentState.address

        val isValidUsername = UserValidator.isValidUsername(username)
        val isValidNumber = UserValidator.isValidMobileNumber(mobile_number)
        val isValidAddress = UserValidator.isValidAddress(address)

        setState { state ->
            state.copy(isValidUsername = isValidUsername,
                       isValidNumber = isValidNumber,
                       isValidAddress = isValidAddress)
        }

        return isValidUsername && isValidNumber && isValidAddress
    }


}