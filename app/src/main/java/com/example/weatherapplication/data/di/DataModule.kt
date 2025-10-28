package com.example.weatherapplication.data.di

import com.example.weatherapplication.data.data_sources.remote.ForecastRemoteSource
import com.example.weatherapplication.data.data_sources.remote.ForecastRemoteSourceImpl
import com.example.weatherapplication.data.repository.ForecastRepositoryImpl
import com.example.weatherapplication.domain.repository.ForecastRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Module
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindForecastRemoteSourceImpl(forecastRemoteSourceImpl: ForecastRemoteSourceImpl): ForecastRemoteSource

    @Binds
    @Singleton
    abstract fun bindForecastRepositoryImpl(forecastRepositoryImpl: ForecastRepositoryImpl): ForecastRepository
}