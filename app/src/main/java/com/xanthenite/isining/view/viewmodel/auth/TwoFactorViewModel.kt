package com.xanthenite.isining.view.viewmodel.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.AuthRepository
import com.xanthenite.isining.core.session.SessionManager
import com.xanthenite.isining.view.state.auth.TwoFactorState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TwoFactorViewModel @Inject constructor(
    private val twoFactorRepository : AuthRepository,
    private val sessionManager : SessionManager
) :BaseViewModel<TwoFactorState>(initialState = TwoFactorState())
{
    init {
        val isLoggedIn = sessionManager.getToken() != null
        setState { TwoFactorState(isLoggedIn = isLoggedIn)}
    }

    fun setCode(code : String)
    {
        setState { state -> state.copy(verification_code = code) }
    }

    fun authenticate(){
        viewModelScope.launch {
            val code = currentState.verification_code

            setState { state -> state.copy(isLoading = true) }


            val response = twoFactorRepository.authenticate(code, sessionManager.getEmail().orEmpty())

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
                Log.e("TwoFactorViewModel", "$message")
            }
        }
    }
}