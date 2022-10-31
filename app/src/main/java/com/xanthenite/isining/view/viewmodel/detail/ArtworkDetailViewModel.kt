package com.xanthenite.isining.view.viewmodel.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.model.Artwork
import com.xanthenite.isining.core.repository.ArtworkRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.detail.ArtworkDetailState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArtworkDetailViewModel @AssistedInject constructor(
        @Assisted private val id : Int ,
        @RemoteRepository val artworkRepository : ArtworkRepository ,
        private val connectivityObserver: ConnectivityObserver
) : BaseViewModel<ArtworkDetailState>(initialState = ArtworkDetailState(
        data = Artwork(
            id = null,
            title = null,
            description = null,
            length = null,
            width = null,
            type = null,
            price = null,
            user_name = null,
            user = null,
            pictures = listOf(null),
            sold = null)))
{
    private lateinit var selectedArtwork : Artwork

    init
    {
        loadArtwork()
        observeConnectivity()
    }

    private fun observeConnectivity() {
        connectivityObserver.connectionState
                .distinctUntilChanged()
                .map { it === ConnectionState.Available }
                .onEach { setState { state -> state.copy(isConnectivityAvailable = it) } }
                .launchIn(viewModelScope)
    }

    fun loadArtwork() {
        viewModelScope.launch {
            setState { state -> state.copy(isLoading = true) }
            val artwork = artworkRepository.getArtworkById(id).firstOrNull()
            if (artwork != null) {
                selectedArtwork = artwork
                setState { state ->
                    state.copy(isLoading = false, data = artwork)
                }
                Log.d("Artwork Data" , "$artwork")
            } else {
                setState { state -> state.copy(isLoading = false, error = "An unknown error occurred") }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id : Int): ArtworkDetailViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
                assistedFactory: Factory,
                id: Int): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}