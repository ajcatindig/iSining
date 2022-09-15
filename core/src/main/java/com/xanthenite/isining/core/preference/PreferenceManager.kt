package com.xanthenite.isining.core.preference

import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * Preference Manager for the application.
 * Currently it just keeps UI mode preference such as Light mode or dark mode.
 */
@Singleton
interface PreferenceManager {
    /**
     * Flow of the UI mode preference
     *
     * true - Dark mode enabled
     * false - Dark mode disabled (i.e. Light mode)
     */
    val uiModeFlow: Flow<Boolean>

    /**
     * Updates the preference for UI mode.
     *
     * @param enable Sets Dark Mode if true otherwise Light mode.
     */
    suspend fun setDarkMode(enable: Boolean)
}