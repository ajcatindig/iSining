package com.xanthenite.isining.connectivity

import android.net.ConnectivityManager
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.core.connectivity.ConnectivityObserver
import com.xanthenite.isining.utils.currentConnectivityState
import com.xanthenite.isining.utils.observeConnectivityAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalCoroutinesApi::class)
class ConnectivityObserverImpl(
        private val connectivityManager: ConnectivityManager
        ) : ConnectivityObserver
{

    override val connectionState: Flow<ConnectionState>
        get() = connectivityManager.observeConnectivityAsFlow()

    override val currentConnectionState: ConnectionState
        get() = connectivityManager.currentConnectivityState
}