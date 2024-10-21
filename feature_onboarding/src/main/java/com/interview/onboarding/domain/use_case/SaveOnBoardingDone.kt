package com.interview.onboarding.domain.use_case

import com.interview.onboarding.domain.repository.OnBoardingRepository

class SaveOnBoardingDone(
    private val onBoardingRepository: OnBoardingRepository
) {
    suspend operator fun invoke() {
        onBoardingRepository.saveAppEntry()
    }
}