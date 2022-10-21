package com.xanthenite.isining.view.viewmodel.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.model.Artist
import com.xanthenite.isining.core.repository.ArtistRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.detail.ArtistDetailState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArtistDetailViewModel @AssistedInject constructor(
        @Assisted private val id : Int ,
        @RemoteRepository val artistRepository : ArtistRepository ,
        private val connectivityObserver: ConnectivityObserver
) : BaseViewModel<ArtistDetailState>(initialState = ArtistDetailState(
        data = Artist(
            id = null,
            name = null,
            first_name = null,
            last_name = null,
            birth_date = null,
            type = null,
            gender = null,
            number = null,
            bio = null,
            email = null,
            profile_photo_path = null)))
{
    private lateinit var selectedArtist : Artist

    init
    {
        loadArtist()
        observeConnectivity()
    }

    private fun observeConnectivity() {
        connectivityObserver.connectionState
                .distinctUntilChanged()
                .map { it === ConnectionState.Available }
                .onEach { setState { state -> state.copy(isConnectivityAvailable = it) } }
                .launchIn(viewModelScope)
    }

    fun loadArtist() {
        viewModelScope.launch {
            setState { state -> state.copy(isLoading = true) }
            val artist = artistRepository.getArtistById(id).firstOrNull()
            if (artist != null) {
                selectedArtist = artist
                setState { state ->
                    state.copy(isLoading = false, data = artist)
                }
                Log.d("Artwork Data" , "$artist")
            } else {
                setState { state -> state.copy(isLoading = false, error = "An unknown error occurred") }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id : Int): ArtistDetailViewModel
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