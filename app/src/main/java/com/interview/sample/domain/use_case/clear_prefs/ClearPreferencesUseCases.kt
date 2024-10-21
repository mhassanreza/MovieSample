package com.interview.sample.domain.use_case.clear_prefs

data class ClearPreferencesUseCases(
    val clearAuthTokenUseCase: ClearAuthTokenUseCase,
    val clearUserUseCase: ClearUserUseCase,
    val clearLoggedInStatus: ClearLoggedInStatusUseCase
)