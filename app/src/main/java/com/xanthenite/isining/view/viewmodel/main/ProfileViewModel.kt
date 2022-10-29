package com.xanthenite.isining.view.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.model.User
import com.xanthenite.isining.core.preference.PreferenceManager
import com.xanthenite.isining.core.repository.UserRepository
import com.xanthenite.isining.core.session.SessionManager
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.main.ExhibitState
import com.xanthenite.isining.view.state.main.ProfileState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
        private val preferenceManager : PreferenceManager ,
        private val connectivityObserver: ConnectivityObserver,
        private val sessionManager : SessionManager,
        @RemoteRepository val userRepository : UserRepository
) : BaseViewModel<ProfileState>(
        initialState = ProfileState(data = User(
                id = null,
                name = null,
                first_name = null,
                last_name = null,
                birthdate = null,
                type = null,
                gender = null,
                mobile_number = null,
                email = null,
                profile_photo_path = null,
                bio = null,
                address = null)))
{
    init {
        observeConnectivity()
        checkUserSession()
        getCurrentUser()
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

    fun getCurrentUser()
    {
        userRepository.getCurrentUser()
                .distinctUntilChanged()
                .onEach { response ->
                    response.onSuccess { data ->
                        setState { state -> state.copy(isLoading = false, data = data) }
                    }.onFailure { message ->
                        setState { state -> state.copy(isLoading = false, error = message) }
                    }
                }.onStart { setState { state -> state.copy(isLoading = true) } }
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