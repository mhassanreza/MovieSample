package com.interview.onboarding.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.interview.common.shared.BaseNavRoutes
import com.interview.onboarding.presentation.OnBoardingScreen
import com.interview.onboarding.presentation.onboarding.viewmodel.OnboardingViewModel
import kotlinx.serialization.Serializable

sealed class OnBoardingGraphRoutes() : BaseNavRoutes {
    @Serializable
    data object OnBoardingGraph : OnBoardingGraphRoutes()
}

sealed class OnBoardingScreensRoute() {
    @Serializable
    data object OnboardingScreen : OnBoardingScreensRoute()
}

fun NavGraphBuilder.onBoardingNavGraph(
    onMoveToHome: () -> Unit
) {
    navigation<OnBoardingGraphRoutes.OnBoardingGraph>(
        startDestination = OnBoardingScreensRoute.OnboardingScreen
    ) {
        composable<OnBoardingScreensRoute.OnboardingScreen> {
            // Initializing the view model :->
            val viewModel = hiltViewModel<OnboardingViewModel>()
            // Calling Onboarding Screen :->
            OnBoardingScreen {
                viewModel.onEvent(it)
                onMoveToHome()
            }
        }

    }
}
