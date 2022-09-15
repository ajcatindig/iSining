package com.xanthenite.isining.core.session

import javax.inject.Singleton

/**
 * Manages session related information in persistent storage.
 */
@Singleton
interface SessionManager {

    /**
     * Saves [token] in persistence storage.
     * The saved token can be retrieved anytime in the future using [getToken]
     */
    fun saveToken(token: String?)

    /**
     * Returns the current available token.
     */
    fun getToken(): String?
}