package com.xanthenite.isining.view.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.preference.PreferenceManager
import com.xanthenite.isining.core.session.SessionManager
import com.xanthenite.isining.view.state.main.ExhibitState
import com.xanthenite.isining.view.state.main.ProfileState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
        private val preferenceManager : PreferenceManager ,
        private val connectivityObserver: ConnectivityObserver,
        private val sessionManager : SessionManager
) : BaseViewModel<ProfileState>(initialState = ProfileState())
{
    init {
        observeConnectivity()
        checkUserSession()
    }

    fun setDarkMode(enable: Boolean) {
        viewModelScope.launch {
            preferenceManager.setDarkMode(enable)
        }
    }

    private fun observeConnectivity() {
        connectivityObserver.connectionState
                .distinctUntilChanged()
                .map { it === ConnectionState.Available }
                .onEach { setState { state -> state.copy(isConnectivityAvailable = it) } }
                .launchIn(viewModelScope)
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

    companion object {
        private const val TAG = "ProfileViewModel"
    }
}