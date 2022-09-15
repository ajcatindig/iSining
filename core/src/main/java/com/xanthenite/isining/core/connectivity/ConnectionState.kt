package com.xanthenite.isining.core.connectivity

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}