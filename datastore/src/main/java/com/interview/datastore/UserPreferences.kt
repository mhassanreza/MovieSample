package com.interview.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USERNAME_KEY = stringPreferencesKey("username_key")
        val TOKEN_KEY = stringPreferencesKey("token_key")
    }

    // Save data to DataStore
    suspend fun saveUser(email: String, accessToken: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = email
            preferences[TOKEN_KEY] = accessToken.toString()
        }
    }

    // Retrieve data from DataStore
    val user: Flow<User> = dataStore.data.map { preferences ->
        val username = preferences[USERNAME_KEY] ?: ""
        val token = preferences[TOKEN_KEY] ?: ""
        User(username, token)
    }

    suspend fun clearUser() {
        dataStore.edit { preferences ->
            preferences.remove(USERNAME_KEY)
            preferences.remove(TOKEN_KEY)
        }
    }
}
