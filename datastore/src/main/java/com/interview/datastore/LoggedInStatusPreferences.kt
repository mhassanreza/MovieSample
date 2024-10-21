package com.interview.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoggedInStatusPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        val LOGGED_IN_STATUS_KEY = booleanPreferencesKey("is_user_logged_in")
    }

    // Save data to DataStore
    suspend fun saveUserLoggedInStatus(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[LOGGED_IN_STATUS_KEY] = status
        }
    }

    // Retrieve data from DataStore
    val isUserLoggedIn: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[LOGGED_IN_STATUS_KEY] ?: false
    }

    suspend fun clearLoggedInStatus() {
        dataStore.edit { preferences ->
            preferences.remove(LOGGED_IN_STATUS_KEY)
        }
    }
}
