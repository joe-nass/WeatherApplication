package com.example.weatherapplication.domain.use_cases

import android.location.Location
import com.example.weatherapplication.domain.model.UserLocation
import com.example.weatherapplication.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(private val locationRepository: LocationRepository) :
    UseCase<Unit, Flow<Location>> {
    override suspend fun invoke(params: Unit): Flow<Location> {
        return locationRepository.getUserLocation()
    }
}