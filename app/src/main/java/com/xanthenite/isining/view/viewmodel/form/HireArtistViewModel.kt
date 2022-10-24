package com.xanthenite.isining.view.viewmodel.form

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.TransactionRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.form.HireArtistState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HireArtistViewModel @Inject constructor(
        @RemoteRepository val transactionRepository : TransactionRepository
) : BaseViewModel<HireArtistState>(initialState = HireArtistState())
{
    fun setArtistId(id : Int) {
        setState { state -> state.copy(artist_user_id = id) }
    }

    fun setPrice(price : String) {
        setState { state -> state.copy(price = price) }
    }

    fun setAddress(address : String) {
        setState { state -> state.copy(address = address) }
    }

    fun setDescription(description : String) {
        setState { state -> state.copy(description = description) }
    }

    fun postCommission(id : Int) {
        viewModelScope.launch {
            val price = currentState.price
            val address = currentState.address
            val description = currentState.description

            setState { state -> state.copy(isLoading = true) }

            val response = transactionRepository.postCommission(id, price.toDouble(), description, address)

            response.onSuccess { message ->
                setState { state ->
                    state.copy(isLoading = false,
                               isSuccess = message.message,
                               error = null,
                               price = "",
                               address = "",
                               description = "")
                }
                Log.d("HireArtistViewModel" , "$message")
            }.onFailure { message ->
                setState { state ->
                    state.copy(isLoading = false,
                               isSuccess = null,
                               error = message,
                               price = "",
                               address = "",
                               description = "")
                }
            }
        }
    }
}