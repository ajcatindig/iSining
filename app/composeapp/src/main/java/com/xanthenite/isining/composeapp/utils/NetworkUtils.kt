package com.xanthenite.isining.composeapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.xanthenite.isining.core.connectivity.ConnectionState
import com.xanthenite.isining.utils.connectivityManager
import com.xanthenite.isining.utils.currentConnectivityState
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import com.xanthenite.isining.utils.observeConnectivityAsFlow

@Composable
fun currentConnectionState() : ConnectionState {
    val connectivityManager = LocalContext.current.connectivityManager
    return remember { connectivityManager.currentConnectivityState }
}

@Composable
fun connectivityState() : State<ConnectionState>
{
    val connectivityManager = LocalContext.current.connectivityManager
    return produceState(initialValue = connectivityManager.currentConnectivityState) {
        connectivityManager.observeConnectivityAsFlow().collect { value = it }
    }
}