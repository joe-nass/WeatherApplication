package com.example.weatherapplication.data.di

import com.example.weatherapplication.domain.repository.ForecastRepository
import com.example.weatherapplication.domain.repository.LocationRepository
import com.example.weatherapplication.domain.use_cases.GetForecastUseCase
import com.example.weatherapplication.domain.use_cases.GetUserLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideGetForecastUseCase(forecastRepository: ForecastRepository): GetForecastUseCase {
        return GetForecastUseCase(forecastRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserLocationUseCase(locationRepository: LocationRepository): GetUserLocationUseCase {
        return GetUserLocationUseCase(locationRepository)
    }
}