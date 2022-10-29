package com.xanthenite.isining.view.viewmodel.form

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.UserRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.utils.validator.AuthValidator
import com.xanthenite.isining.view.state.form.ChangePassState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  ChangePassViewModel @Inject constructor(
        @RemoteRepository val userRepository : UserRepository
) : BaseViewModel<ChangePassState>(initialState = ChangePassState())
{
    fun setCurrentPassword(password : String) {
        setState { state -> state.copy(currentPassword = password) }
    }

    fun setNewPassword(newPassword : String) {
        setState { state -> state.copy(newPassword = newPassword) }
    }

    fun setConfirmPassword(newPassword : String) {
        setState { state -> state.copy(confirmPassword = newPassword) }
    }

    fun changePassword() {
        if (!validatePassword()) return

        viewModelScope.launch {
            val currentPassword = currentState.currentPassword
            val newPassword = currentState.newPassword
            val confirmPassword = currentState.confirmPassword

            setState { state -> state.copy(isLoading = true) }

            val response = userRepository.changePassword(currentPassword, newPassword, confirmPassword)

            response.onSuccess { message ->
                setState { state ->
                    state.copy(isLoading = false,
                               isSuccess = message.message,
                               error = null,
                               currentPassword = "",
                               newPassword = "",
                               confirmPassword = "")
                }
            }.onFailure { message ->
                setState { state ->
                    state.copy(isLoading = false,
                               isSuccess = null,
                               error = "Invalid Password",
                               currentPassword = "",
                               newPassword = "",
                               confirmPassword = "")
                }
            }
        }
    }

    private fun validatePassword() : Boolean {
        val currentPassword = currentState.currentPassword
        val newPassword = currentState.newPassword
        val confirmPassword = currentState.confirmPassword

        val isValidNewPassword = AuthValidator.isValidPassword(newPassword)
        val arePasswordAndConfirmPasswordSame = AuthValidator.isPasswordAndConfirmPasswordSame(
                newPassword,
                confirmPassword)

        setState { state ->
            state.copy(
                    isValidNewPass = isValidNewPassword,
                    isValidConfirmPass = arePasswordAndConfirmPasswordSame)
        }

        return isValidNewPassword && arePasswordAndConfirmPasswordSame
    }
}