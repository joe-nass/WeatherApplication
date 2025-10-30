package com.example.weatherapplication.core.helpers

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.os.Looper
import com.example.weatherapp.utils.LocationUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.common.api.ResolvableApiException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext

class LocationManager @Inject constructor(
    @ApplicationContext private val context: Context,
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
        ).setMinUpdateIntervalMillis(3000L).build()

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

    fun checkLocationSettings(
        intervalMillis: Long = 3000L,
        priority: Int = Priority.PRIORITY_HIGH_ACCURACY,
        onSatisfied: () -> Unit,
        onResolvable: (PendingIntent) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val request = LocationRequest.Builder(priority, intervalMillis).build()
        val settingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(request)
            .setAlwaysShow(true)
            .build()

        val client = LocationServices.getSettingsClient(context)
        client.checkLocationSettings(settingsRequest)
            .addOnSuccessListener { onSatisfied() }
            .addOnFailureListener { ex ->
                when (ex) {
                    is ResolvableApiException -> onResolvable(ex.resolution)
                    else -> onError(ex)
                }
            }
    }
}