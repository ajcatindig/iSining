package com.xanthenite.isining.view.viewmodel.form

import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.TransactionRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.form.PaymentChannelState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class PaymentChannelViewModel @Inject constructor(
        @RemoteRepository val transactionRepository : TransactionRepository
) : BaseViewModel<PaymentChannelState>(initialState = PaymentChannelState())
{
    init
    {
        getPaymentChannels()
    }

    fun getPaymentChannels(){
        transactionRepository.getPaymentChannels()
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
        private const val TAG = "PaymentChannelViewModel"
    }
}