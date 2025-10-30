package com.example.weatherapplication.features.weather

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.utils.LocationUtils
import com.example.weatherapplication.features.weather.presentation.screen.ForecastScreen

@Composable
fun ForecastRoute(viewModel: CurrentWeatherViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val utils = remember { LocationUtils(context) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.values.any { it }
        if (granted) viewModel.sendIntent(ForecastContract.ForecastIntent.LoadWithUserLocation)
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

    ForecastScreen(uiState)
}
