package com.interview.onboarding.domain.use_case

import com.interview.onboarding.domain.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow

class CheckOnBoardingAlreadyDone(
    private val onBoardingRepository: OnBoardingRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return onBoardingRepository.readAppEntry()
    }
}