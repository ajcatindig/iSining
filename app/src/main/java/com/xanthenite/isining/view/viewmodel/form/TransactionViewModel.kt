package com.xanthenite.isining.view.viewmodel.form

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.repository.TransactionRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.form.TransactionState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
        private val connectivityObserver: ConnectivityObserver ,
        @RemoteRepository val transactionRepository : TransactionRepository
) : BaseViewModel<TransactionState>(initialState = TransactionState())
{
    init {
        observeConnectivity()
        getTransactions()
    }

    private fun observeConnectivity() {
        connectivityObserver.connectionState
                .distinctUntilChanged()
                .map { it === ConnectionState.Available }
                .onEach { setState { state -> state.copy(isConnectivityAvailable = it) } }
                .launchIn(viewModelScope)
    }

    fun getTransactions() {
        transactionRepository.getTransactions()
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
        private const val TAG = "TransactionViewModel"
    }
}