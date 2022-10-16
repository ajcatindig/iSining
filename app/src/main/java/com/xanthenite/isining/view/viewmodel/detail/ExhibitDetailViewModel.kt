package com.xanthenite.isining.view.viewmodel.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.core.model.Exhibit
import com.xanthenite.isining.core.repository.Either
import com.xanthenite.isining.core.repository.ExhibitRepository
import com.xanthenite.isining.di.RemoteRepository
import com.xanthenite.isining.view.state.detail.ExhibitDetailState
import com.xanthenite.isining.view.viewmodel.BaseViewModel
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ExhibitDetailViewModel @AssistedInject constructor(
        @Assisted private val id : Int,
        @RemoteRepository val exhibitRepository : ExhibitRepository,
        private val connectivityObserver: ConnectivityObserver
) : BaseViewModel<ExhibitDetailState>(initialState = ExhibitDetailState(
        data = Exhibit(
                id = null,
                title = null,
                description = null,
                cover_path = null,
                start_date = null,
                end_date = null,
                artwork = emptyList())))
{
private lateinit var selectedExhibit : Exhibit

    init
    {
        loadExhibit()
        observeConnectivity()
    }

    private fun observeConnectivity() {
        connectivityObserver.connectionState
                .distinctUntilChanged()
                .map { it === ConnectionState.Available }
                .onEach { setState { state -> state.copy(isConnectivityAvailable = it) } }
                .launchIn(viewModelScope)
    }

    fun loadExhibit() {
        viewModelScope.launch {
            setState { state -> state.copy(isLoading = true) }
            val exhibit = exhibitRepository.getExhibitById(id).firstOrNull()
            if (exhibit != null) {
                selectedExhibit = exhibit
                setState { state ->
                    state.copy(isLoading = false, data = exhibit)
                }
                Log.d("Exhibit Data", "$exhibit")
            } else {
                setState { state -> state.copy(isLoading = false, error = "An unknown error occurred") }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id : Int): ExhibitDetailViewModel
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

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AssistedInjectModule