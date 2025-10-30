package com.example.weatherapplication.domain.repository

import android.location.Location
import com.example.weatherapplication.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getUserLocation(): Flow<Location>
}