package com.interview.onboarding.presentation.onboarding.viewmodel

sealed class OnBoardingEvents {
    data object SaveOnBoardingDone : OnBoardingEvents()
}