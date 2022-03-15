package com.demo.cypressassignment.di

import com.demo.cypressassignment.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return ApiService.create()
    }
}