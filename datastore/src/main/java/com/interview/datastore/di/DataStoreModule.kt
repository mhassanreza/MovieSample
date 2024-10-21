package com.interview.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.interview.datastore.LoggedInStatusPreferences
import com.interview.datastore.RememberMeCheckedPreferences
import com.interview.datastore.TokenPreferences
import com.interview.datastore.UserCredentialsPreferences
import com.interview.datastore.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.dataStoreFile("user_prefs.preferences_pb") }
        )
    }

    @Provides
    @Singleton
    fun provideUserPreferences(dataStore: DataStore<Preferences>): UserPreferences {
        return UserPreferences(dataStore)
    }

    @Provides
    @Singleton
    fun provideTokenPreferences(dataStore: DataStore<Preferences>): TokenPreferences {
        return TokenPreferences(dataStore)
    }

    @Provides
    @Singleton
    fun provideLoggedInStatusPreferences(dataStore: DataStore<Preferences>): LoggedInStatusPreferences {
        return LoggedInStatusPreferences(dataStore)
    }

    @Provides
    @Singleton
    fun provideUserCredentialsPreferences(dataStore: DataStore<Preferences>): UserCredentialsPreferences {
        return UserCredentialsPreferences(dataStore)
    }

    @Provides
    @Singleton
    fun provideRememberMeCheckedPreferences(dataStore: DataStore<Preferences>): RememberMeCheckedPreferences {
        return RememberMeCheckedPreferences(dataStore)
    }

}