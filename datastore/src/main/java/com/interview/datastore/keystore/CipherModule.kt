package com.interview.datastore.keystore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CipherModule {

    @Provides
    @Singleton
    fun provideCipherManager(): CipherManager {
        return CipherManagerImpl()
    }

}