package com.xanthenite.isining.view.viewmodel.form

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.repository.TransactionRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.form.ListCommissionState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CommiListViewModel  @Inject constructor(
        @RemoteRepository val transactionRepository : TransactionRepository ,
        private val connectivityObserver: ConnectivityObserver
) : BaseViewModel<ListCommissionState>(initialState = ListCommissionState())
{
    init {
        observeConnectivity()
        getCommissions()
    }

    private fun observeConnectivity() {
        connectivityObserver.connectionState
                .distinctUntilChanged()
                .map { it === ConnectionState.Available }
                .onEach { setState { state -> state.copy(isConnectivityAvailable = it) } }
                .launchIn(viewModelScope)
    }

    fun getCommissions() {
        transactionRepository.getCommissions()
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
        private const val TAG = "CommissionListViewModel"
    }
}