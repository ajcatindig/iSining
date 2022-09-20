package com.xanthenite.isining.view.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.preference.PreferenceManager
import com.xanthenite.isining.view.state.main.ExhibitState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExhibitViewModel @Inject constructor(
        private val preferenceManager : PreferenceManager,
        private val connectivityObserver: ConnectivityObserver
) : BaseViewModel<ExhibitState>(initialState = ExhibitState())
{
    init {
        observeConnectivity()
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

    companion object {
        private const val TAG = "ExhibitViewModel"
    }
}