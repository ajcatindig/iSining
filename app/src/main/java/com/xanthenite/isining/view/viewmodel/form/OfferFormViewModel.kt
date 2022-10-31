package com.xanthenite.isining.view.viewmodel.form

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.repository.TransactionRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.utils.validator.UserValidator
import com.xanthenite.isining.view.state.form.OfferState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferFormViewModel @Inject constructor(
        @RemoteRepository val transactionRepository : TransactionRepository
) : BaseViewModel<OfferState>(initialState = OfferState())
{
    fun setArtworkId(id : Int) {
        setState { state -> state.copy(artwork_id = id) }
    }

    fun setPaymentChannelId(id : Int) {
        setState { state -> state.copy(payment_channel_id = id) }
    }

    fun setPrice(price : String) {
        setState { state -> state.copy(price = price) }
    }

    fun setAddress(address : String) {
        setState { state -> state.copy(address = address) }
    }

    fun setNote(note : String?){
        setState { state -> state.copy(note = note) }
    }

    fun postOffer(id : Int) {
        if (!validateInfo()) return
        viewModelScope.launch {
            val payment_channel_id = currentState.payment_channel_id
            val price = currentState.price
            val address = currentState.address
            val note = currentState.note

            setState { state -> state.copy(isLoading = true) }

            val response = transactionRepository.postOffer(id, price?.toDouble(), payment_channel_id , note, address)

            response.onSuccess { message ->
                setState { state ->
                    state.copy(isLoading = false,
                               isSuccess = message.message,
                               error = null,
                               payment_channel_id = 0,
                               price = "",
                               address = "",
                               note = "")
                }
                Log.d("OfferFormViewModel", "$message")
            }.onFailure { message ->
                setState { state ->
                    state.copy(isLoading = false,
                               isSuccess = null,
                               error = "Offer price should be greater than or equal to the original price.",
                               payment_channel_id = 0,
                               price = "",
                               address = "",
                               note = "")
                }
                Log.e("OfferFormViewModel" , message)
            }
        }
    }

    private fun validateInfo() : Boolean {
        val address = currentState.address
        val isValidAddress = UserValidator.isValidAddress(address)

        setState { state ->
            state.copy(isValidAddress = isValidAddress)
        }

        return isValidAddress
    }
}