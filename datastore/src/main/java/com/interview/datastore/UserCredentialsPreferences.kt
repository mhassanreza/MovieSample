package com.interview.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserCredentialsPreferences(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USERNAME_KEY = stringPreferencesKey("saved_username_key")
        val PASSWORD_KEY = stringPreferencesKey("saved_password_key")
    }

    // Save data to DataStore
    suspend fun saveUserCredentials(username: String, password: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
            preferences[PASSWORD_KEY] = password
        }
    }

    // Retrieve data from DataStore
    val userCredentials: Flow<Pair<String, String>> = dataStore.data.map { preferences ->
        val username = preferences[USERNAME_KEY] ?: ""
        val password = preferences[PASSWORD_KEY] ?: ""
        Pair(username, password)
    }

    suspend fun clearUserCredentials() {
        dataStore.edit { preferences ->
            preferences.remove(USERNAME_KEY)
            preferences.remove(PASSWORD_KEY)
        }
    }
}
