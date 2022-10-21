package com.xanthenite.isining.view.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.preference.PreferenceManager
import com.xanthenite.isining.core.repository.ExhibitRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.main.ExhibitState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PastExhibitsViewModel @Inject constructor(
        @RemoteRepository val exhibitRepository : ExhibitRepository
) : BaseViewModel<ExhibitState>(initialState = ExhibitState())
{
    init {
        getPastExhibits()
    }

    fun getPastExhibits() {
        exhibitRepository.getPastExhibits()
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
        private const val TAG = "PastExhibitViewModel"
    }
}