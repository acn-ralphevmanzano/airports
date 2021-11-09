package com.example.airports.di

import com.example.airports.data.api.ApiService
import com.example.airports.data.repository.AirportRepoImpl
import com.example.airports.domain.repository.AirportRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideAirportRepo(
        apiService: ApiService,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): AirportRepo {
        return AirportRepoImpl(apiService, dispatcher)
    }

}