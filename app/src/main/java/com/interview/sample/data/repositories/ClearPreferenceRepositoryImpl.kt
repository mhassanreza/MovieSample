package com.interview.sample.data.repositories

import com.interview.datastore.LoggedInStatusPreferences
import com.interview.datastore.TokenPreferences
import com.interview.datastore.UserPreferences
import com.interview.sample.domain.repository.ClearPreferenceRepository
import javax.inject.Inject

class ClearPreferenceRepositoryImpl @Inject constructor(
    private val userPreferences: UserPreferences,
    private val loginStatusPreferences: LoggedInStatusPreferences,
    private val tokenPreferences: TokenPreferences
) : ClearPreferenceRepository {
    override suspend fun clearUser() {
        userPreferences.clearUser()
    }

    override suspend fun clearLoggedInStatus() {
        loginStatusPreferences.clearLoggedInStatus()
    }

    override suspend fun clearAuthToken() {
        tokenPreferences.clearToken()
    }
}