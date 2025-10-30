package com.example.weatherapplication.core.helpers

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.example.weatherapp.utils.LocationUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LocationManager @Inject constructor(
    private val locationUtils: LocationUtils,
    private val fusedClient: FusedLocationProviderClient
) {
    @SuppressLint("MissingPermission")
    fun getUserLocation(): Flow<Location> = callbackFlow {
        if (!locationUtils.hasLocationPermission()) {
            throw Exception("Location permission not granted")
        }

        if (!locationUtils.isLocationEnabled()) {
            throw Exception("Location is turned off")
        }

        val request = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            3000L
        )
            .setMinUpdateIntervalMillis(3000L)
            .build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val location = result.lastLocation ?: return
                trySend(location).isSuccess
            }
        }

        fusedClient.requestLocationUpdates(
            request,
            callback,
            Looper.getMainLooper()
        )

        awaitClose {
            fusedClient.removeLocationUpdates(callback)
        }
    }
}