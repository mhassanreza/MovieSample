package com.interview.onboarding.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    suspend fun saveAppEntry()
    fun readAppEntry() : Flow<Boolean>
}