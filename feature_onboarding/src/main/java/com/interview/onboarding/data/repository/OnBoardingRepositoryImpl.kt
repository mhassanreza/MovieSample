package com.interview.onboarding.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.interview.onboarding.domain.repository.OnBoardingRepository
import com.interview.onboarding.util.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnBoardingRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : OnBoardingRepository {

    companion object {
        val ON_BOARDING_DONE = booleanPreferencesKey(name = Constant.ON_BOARDING_DONE_ALREADY)
    }

    override suspend fun saveAppEntry() {
        Log.d("LocalUserManager", "Saving app entry status to true")
        dataStore.edit { settings ->
            settings[ON_BOARDING_DONE] = true // Store the value in DataStore
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        Log.d("LocalUserManager", "Reading app entry status")
        return dataStore.data
            .map { preferences ->
                val appEntry = preferences[ON_BOARDING_DONE] ?: false
                Log.d("LocalUserManager", "App entry status: $appEntry")
                appEntry
            }
    }
}