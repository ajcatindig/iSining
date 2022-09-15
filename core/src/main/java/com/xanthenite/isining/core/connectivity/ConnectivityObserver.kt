package com.xanthenite.isining.core.connectivity

import kotlinx.coroutines.flow.Flow

/**
 * A utility for observing the connectivity status
 */
interface ConnectivityObserver {
    /**
     * Gives the realtime updates of a [ConnectionState]
     */
    val connectionState: Flow<ConnectionState>

    /**
     * Retrieves the current [ConnectionState]
     */
    val currentConnectionState: ConnectionState
}