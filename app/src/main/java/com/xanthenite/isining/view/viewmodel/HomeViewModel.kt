package com.xanthenite.isining.view.viewmodel

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.session.SessionManager
import com.xanthenite.isining.view.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val sessionManager: SessionManager
) : BaseViewModel<HomeState>(initialState = HomeState()) {

    init {
        checkUserSession()
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.saveToken(null)
            setState { state -> state.copy(isUserLoggedIn = false) }
        }
    }

    private fun checkUserSession() {
        setState { it.copy(isUserLoggedIn = sessionManager.getToken() != null) }
    }

}