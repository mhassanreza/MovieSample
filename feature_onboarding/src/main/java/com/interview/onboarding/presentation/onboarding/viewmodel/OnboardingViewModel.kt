package com.interview.onboarding.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.onboarding.domain.use_case.OnBoardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appEntryUseCases: OnBoardingUseCases
) : ViewModel() {
    fun onEvent(event: OnBoardingEvents) {
        when (event) {
            is OnBoardingEvents.SaveOnBoardingDone -> {
                saveOnBoardingDone()
            }
        }
    }

    private fun saveOnBoardingDone() {
        viewModelScope.launch {
            appEntryUseCases.saveOnBoardingDone()
        }
    }
}