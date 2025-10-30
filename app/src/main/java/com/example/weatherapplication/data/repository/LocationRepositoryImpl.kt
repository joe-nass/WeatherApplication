package com.example.weatherapplication.data.repository

import android.location.Location
import com.example.weatherapplication.core.helpers.LocationManager
import com.example.weatherapplication.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val locationManager: LocationManager) :
    LocationRepository {

    override suspend fun getUserLocation(): Flow<Location> {
        return locationManager.getUserLocation()
    }
}