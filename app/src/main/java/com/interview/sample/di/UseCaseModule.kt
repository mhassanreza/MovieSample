package com.interview.sample.di

import com.interview.sample.domain.repository.ClearPreferenceRepository
import com.interview.sample.domain.repository.HomeRepository
import com.interview.sample.domain.use_case.clear_prefs.ClearAuthTokenUseCase
import com.interview.sample.domain.use_case.clear_prefs.ClearLoggedInStatusUseCase
import com.interview.sample.domain.use_case.clear_prefs.ClearPreferencesUseCases
import com.interview.sample.domain.use_case.clear_prefs.ClearUserUseCase
import com.interview.sample.domain.use_case.home.GetHomeMovieListingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideHomeUseCases(homeRepository: HomeRepository): GetHomeMovieListingUseCase {
        return GetHomeMovieListingUseCase(
            homeRepository = homeRepository
        )
    }

    @Provides
    @Singleton
    fun provideClearPreferencesUseCases(clearPreferenceRepository: ClearPreferenceRepository): ClearPreferencesUseCases {
        return ClearPreferencesUseCases(
            clearAuthTokenUseCase = ClearAuthTokenUseCase(clearPreferenceRepository),
            clearUserUseCase = ClearUserUseCase(clearPreferenceRepository),
            clearLoggedInStatus = ClearLoggedInStatusUseCase(clearPreferenceRepository)
        )
    }
}