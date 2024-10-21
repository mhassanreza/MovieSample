package com.interview.sample.domain.repository

interface ClearPreferenceRepository {
    suspend fun clearUser()
    suspend fun clearLoggedInStatus()
    suspend fun clearAuthToken()
}