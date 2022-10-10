package com.xanthenite.isining.view.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.core.model.Featured
import com.xanthenite.isining.core.model.User
import com.xanthenite.isining.core.preference.PreferenceManager
import com.xanthenite.isining.core.repository.FeaturedRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.main.HomeState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val preferenceManager : PreferenceManager,
        private val connectivityObserver: ConnectivityObserver,
        @RemoteRepository val featuredRepository : FeaturedRepository
) : BaseViewModel<HomeState>(initialState = HomeState(data = Featured(
        user = User(
                id = null,
                name = null,
                first_name = null,
                last_name = null,
                birthdate = null,
                type = null,
                gender = null,
                number = null,
                email = null,
                profile_photo_path = null
                   ),
        artwork = Artwork(
                id = null,
                title = null,
                description = null,
                length = null,
                width = null,
                type = null,
                price = null,
                pictures = listOf(null),
                user_name = null))))
{

    init {
        observeConnectivity()
        getCurrentFeatured()
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

    fun getCurrentFeatured()
    {
        featuredRepository.getCurrentFeatured()
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

    companion object {
        private const val TAG = "HomeViewModel"
    }

}