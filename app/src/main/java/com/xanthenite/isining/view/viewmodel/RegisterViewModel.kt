package com.xanthenite.isining.view.viewmodel

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.AuthRepository
import com.xanthenite.isining.core.session.SessionManager
import com.xanthenite.isining.utils.validator.AuthValidator
import com.xanthenite.isining.view.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
        private val registerRepository : AuthRepository,
        private val sessionManager : SessionManager)
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

                        setState { state -> state.copy(isLoading = true) }

                        val response = registerRepository.addUser(username, email, password)

                        /*TODO: Implement Register */
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