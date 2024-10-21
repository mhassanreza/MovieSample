package com.interview.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RememberMeCheckedPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        val REMEMBER_ME_CHECKED_KEY = booleanPreferencesKey("is_remember_me_checked")
    }

    // Save data to DataStore
    suspend fun saveRememberMeChecked(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[REMEMBER_ME_CHECKED_KEY] = status
        }
    }

    // Retrieve data from DataStore
    val isRememberMeChecked: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[REMEMBER_ME_CHECKED_KEY] ?: false
    }

    suspend fun clearRememberMeChecked() {
        dataStore.edit { preferences ->
            preferences.remove(REMEMBER_ME_CHECKED_KEY)
        }
    }
}
