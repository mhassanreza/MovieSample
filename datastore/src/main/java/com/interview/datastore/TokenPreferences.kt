package com.interview.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token_key")
    }

    // Save data to DataStore
    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    // Retrieve data from DataStore
    val token: Flow<String> = dataStore.data.map { preferences ->
        preferences[TOKEN_KEY] ?: "should return null instead of this test string...."
    }


    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}
