package com.interview.onboarding.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.interview.onboarding.data.repository.OnBoardingRepositoryImpl
import com.interview.onboarding.domain.repository.OnBoardingRepository
import com.interview.onboarding.domain.use_case.CheckOnBoardingAlreadyDone
import com.interview.onboarding.domain.use_case.OnBoardingUseCases
import com.interview.onboarding.domain.use_case.SaveOnBoardingDone
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        dataStore: DataStore<Preferences>
    ): OnBoardingRepository = OnBoardingRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        onBoardingRepository: OnBoardingRepository
    ): OnBoardingUseCases = OnBoardingUseCases(
        saveOnBoardingDone = SaveOnBoardingDone(onBoardingRepository),
        checkOnBoardingAlreadyDone = CheckOnBoardingAlreadyDone(onBoardingRepository)
    )

}