package com.example.weatherapplication.features.weather

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.utils.LocationUtils
import com.example.weatherapplication.core.di.LocationEntryPoint
import com.example.weatherapplication.features.weather.presentation.screen.ForecastScreen
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch

@Composable
fun ForecastRoute(viewModel: CurrentWeatherViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val utils = remember { LocationUtils(context) }


    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.values.any { it }
        if (granted) viewModel.sendIntent(ForecastContract.ForecastIntent.LoadWithUserLocation)
    }

    val resolutionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.sendIntent(ForecastContract.ForecastIntent.Retry)
            scope.launch {
                snackBarHostState.showSnackbar("Fetching Location")
            }
        }else {
            scope.launch {
                snackBarHostState.showSnackbar("Please enable Location")
            }
        }
    }

    // Get LocationManager instance via EntryPoint
    val locationManager = remember {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            LocationEntryPoint::class.java
        ).locationManager()
    }

    LaunchedEffect(Unit) {
        if (utils.hasLocationPermission()) {
            viewModel.sendIntent(ForecastContract.ForecastIntent.LoadWithUserLocation)
        } else {
            permissionLauncher.launch(utils.locationPermissions)
        }
    }

    LaunchedEffect(true) {
        viewModel.events.collect { event ->
            when (event) {
                is ForecastContract.ForecastEvent.ShowMessage -> {
                    snackBarHostState.showSnackbar(event.message)
                }

                is ForecastContract.ForecastEvent.NavigateToSearch -> {
                }
            }
        }
    }

    ForecastScreen(uiState, snackBarHostState, onErrorRetry = {
        when {
            !utils.hasLocationPermission() -> {
                permissionLauncher.launch(utils.locationPermissions)
            }
            else -> {
                locationManager.checkLocationSettings(
                    onSatisfied = {
                        viewModel.sendIntent(ForecastContract.ForecastIntent.Retry)
                    },
                    onResolvable = { pendingIntent ->
                        resolutionLauncher.launch(
                            IntentSenderRequest.Builder(pendingIntent.intentSender).build()
                        )
                    },
                    onError = { /* Optionally show a message */ }
                )
            }
        }
    })
}
