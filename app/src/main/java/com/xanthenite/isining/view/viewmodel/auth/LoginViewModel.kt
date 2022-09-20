package com.xanthenite.isining.view.viewmodel.auth

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.AuthRepository
import com.xanthenite.isining.core.session.SessionManager
import com.xanthenite.isining.view.state.auth.LoginState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
        private val loginRepository : AuthRepository,
        private val sessionManager : SessionManager
) : BaseViewModel<LoginState>(initialState = LoginState())
{

    init {
        val isLoggedIn = sessionManager.getToken() != null
        setState { LoginState(isLoggedIn = isLoggedIn) }
    }

    fun setEmail(email : String)
    {
        setState { state -> state.copy(email = email) }
    }

    fun setPassword(password : String)
    {
        setState { state -> state.copy(password = password) }
    }

    fun login(){
        viewModelScope.launch {
            val email = currentState.email
            val password = currentState.password

            setState { state -> state.copy(isLoading = true) }

            val response = loginRepository.getUserByEmailAndPassword(email, password)

            response.onSuccess { authCredential ->
                sessionManager.saveToken(authCredential.token)
                setState { state ->
                    state.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        error = null
                    )
                }
            }.onFailure { message ->
                setState { state ->
                    state.copy(
                        isLoading = false,
                        isLoggedIn = false,
                        error = message
                    )
                }
            }
        }
    }

}