package com.interview.sample.di

import com.interview.datastore.LoggedInStatusPreferences
import com.interview.datastore.TokenPreferences
import com.interview.datastore.UserPreferences
import com.interview.network.services.HomeService
import com.interview.sample.data.repositories.ClearPreferenceRepositoryImpl
import com.interview.sample.data.repositories.HomeRepositoryImpl
import com.interview.sample.domain.repository.ClearPreferenceRepository
import com.interview.sample.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesHomeRepository(
        homeService: HomeService
    ): HomeRepository {
        return HomeRepositoryImpl(homeService)
    }

    @Provides
    @Singleton
    fun providesClearPreferencesRepository(
        userPreferences: UserPreferences,
        loginStatusPreferences: LoggedInStatusPreferences,
        tokenPreferences: TokenPreferences
    ): ClearPreferenceRepository {
        return ClearPreferenceRepositoryImpl(
            userPreferences, loginStatusPreferences, tokenPreferences
        )
    }

}