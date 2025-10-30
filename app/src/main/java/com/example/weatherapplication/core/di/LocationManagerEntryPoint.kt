package com.example.weatherapplication.core.di

import com.example.weatherapplication.core.helpers.LocationManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LocationEntryPoint {
    fun locationManager(): LocationManager
}