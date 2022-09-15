package com.xanthenite.isining.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.xanthenite.isining.core.preference.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.uiModePrefDataStore by preferencesDataStore("ui_mode_pref")

class PreferenceManagerImpl @Inject constructor(
        private val dataStore: DataStore<Preferences>
    ) : PreferenceManager
{

    override val uiModeFlow: Flow<Boolean> = dataStore.data
        .catch {
            it.printStackTrace()
            emit(emptyPreferences())
        }.map { preference -> preference[IS_DARK_MODE] ?: false }

    override suspend fun setDarkMode(enable: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = enable
        }
    }

    companion object {
        val IS_DARK_MODE = booleanPreferencesKey("dark_mode")
    }
}