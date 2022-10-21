package com.xanthenite.isining.view.viewmodel.auth

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.AuthRepository
import com.xanthenite.isining.utils.validator.AuthValidator
import com.xanthenite.isining.view.state.auth.RegisterState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
        private val registerRepository : AuthRepository)
        : BaseViewModel<RegisterState>(initialState = RegisterState())
{
        fun setUsername(username : String) {
                setState { state -> state.copy(username = username) }
        }

        fun setEmail(email : String) {
                setState { state -> state.copy(email = email) }
        }

        fun setPassword(password : String) {
                setState { state -> state.copy(password = password) }
        }

        fun setConfirmPassword(password : String) {
                setState { state -> state.copy(confirmPassword = password) }
        }

        fun register() {
                if (!validateCredentials()) return

                viewModelScope.launch {
                        val username = currentState.username
                        val email = currentState.email
                        val password = currentState.password
                        val password_confirmation = currentState.confirmPassword

                        setState { state -> state.copy(isLoading = true) }

                        val response = registerRepository.addUser(username, email, password, password_confirmation)

                        response.onSuccess { message ->
                                setState { state ->
                                        state.copy(
                                                isLoading = false,
                                                isSuccess = message.message,
                                                error = null,
                                                email = "",
                                                username = "",
                                                password = "",
                                                confirmPassword = "")
                                }
                        }.onFailure {
                                setState { state ->
                                     state.copy(
                                          isLoading = false,
                                          isSuccess = null,
                                          error = "Entered username or email is already taken")
                                }
                        }
                }
        }


        private fun validateCredentials(): Boolean {
                val username = currentState.username
                val email = currentState.email
                val password = currentState.password
                val confirmPassword = currentState.confirmPassword

                val isValidUsername = AuthValidator.isValidUsername(username)
                val isValidEmail = AuthValidator.isValidEmail(email)
                val isValidPassword = AuthValidator.isValidPassword(password)
                val arePasswordAndConfirmPasswordSame = AuthValidator.isPasswordAndConfirmPasswordSame(
                        password,
                        confirmPassword)

                setState { state ->
                        state.copy(
                                isValidUsername = isValidUsername,
                                isValidEmail = isValidEmail,
                                isValidPassword = isValidPassword,
                                isValidConfirmPassword = arePasswordAndConfirmPasswordSame
                                  )
                }

                return isValidUsername && isValidEmail && isValidPassword && arePasswordAndConfirmPasswordSame
        }
}