package com.xanthenite.isining.view.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.preference.PreferenceManager
import com.xanthenite.isining.core.repository.ArtworkRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.main.ArtworkState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
        private val preferenceManager : PreferenceManager,
        private val connectivityObserver: ConnectivityObserver,
        @RemoteRepository val artworkRepository : ArtworkRepository
) : BaseViewModel<ArtworkState>(initialState = ArtworkState())
{
    init {
        observeConnectivity()
        getAllArtworks()
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

    fun getAllArtworks() {
        artworkRepository.getAllArtworks()
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
        private const val TAG = "ArtworkViewModel"
    }
}