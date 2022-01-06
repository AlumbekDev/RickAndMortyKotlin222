package com.example.rickandmortykotlin22.di

import com.example.rickandmortykotlin22.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideCharacterApiService() = retrofitClient.provideCharacterApiService()

    @Provides
    @Singleton
    fun provideEpisodeApiService() = retrofitClient.provideEpisodeApiService()

    @Provides
    @Singleton
    fun provideLocationApiService() = retrofitClient.provideLocationApiService()
}