package com.example.weatherapplication.data.di

import android.content.Context
import androidx.room.Insert
import com.example.weatherapp.utils.LocationUtils
import com.example.weatherapplication.core.helpers.LocationManager
import com.example.weatherapplication.data.repository.LocationRepositoryImpl
import com.example.weatherapplication.domain.repository.LocationRepository
import com.example.weatherapplication.domain.use_cases.GetUserLocationUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationUtils(@ApplicationContext context: Context): LocationUtils {
        return LocationUtils(context)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideLocationManager(
        @ApplicationContext context: Context,
        locationUtils: LocationUtils,
        fusedClient: FusedLocationProviderClient
    ): LocationManager {
        return LocationManager(context, locationUtils, fusedClient)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(locationManager: LocationManager): LocationRepository {
        return LocationRepositoryImpl(locationManager)
    }
}