package com.xanthenite.isining.view.viewmodel.auth

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.AuthRepository
import com.xanthenite.isining.utils.validator.AuthValidator
import com.xanthenite.isining.view.state.auth.ForgotState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
        private val forgotRepository : AuthRepository
) : BaseViewModel<ForgotState>(initialState = ForgotState())
{
    fun setEmail(email : String) {
        setState { state -> state.copy(email = email) }
    }

    fun sendEmail()
    {
        if (!validateCredentials()) return

        viewModelScope.launch {
            val email = currentState.email

            setState { state -> state.copy(isLoading = true) }

            val response = forgotRepository.forgotPassword(email)

            response.onSuccess { message ->
                setState { state ->
                    state.copy(
                            isLoading = false ,
                            isSuccess = message.message,
                            error = null,
                            email = "")
                }
            }.onFailure {
                setState { state ->
                    state.copy(
                            isLoading = false,
                            isSuccess = null,
                            error = "Oops! We can't find a user with that email address.")
                }
            }
        }
    }

    private fun validateCredentials(): Boolean {
        val email = currentState.email
        val isValidEmail = AuthValidator.isValidEmail(email)

        setState { state ->
            state.copy(isValidEmail = isValidEmail)
        }
        return isValidEmail
    }
}