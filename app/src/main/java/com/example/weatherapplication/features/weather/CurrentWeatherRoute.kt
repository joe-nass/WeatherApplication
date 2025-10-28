package com.example.weatherapplication.features.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapplication.core.components.ErrorStateUi
import com.example.weatherapplication.core.components.LoadingStateUi
import com.example.weatherapplication.domain.ForecastFullData
import com.example.weatherapplication.domain.model.Forecast

@Composable
fun ForecastRoute(viewModel: CurrentWeatherViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

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

@Composable
fun ForecastScreen(uiState: ForecastContract.ForecastUiState) {
    Scaffold(topBar = {}, bottomBar = {}) { paddingValues ->
        when {
            uiState.error != null -> {
                ErrorStateUi(msg = uiState.error)
            }

            uiState.isLoading -> {
                LoadingStateUi()
            }

            uiState.data != null && !uiState.isLoading -> {
                SuccessStateUi(modifier = Modifier.padding(paddingValues), forecast = uiState.data)
            }
        }
    }
}



@Composable
fun SuccessStateUi(modifier: Modifier, forecast: ForecastFullData) {
    Column(modifier = modifier.fillMaxSize()) {
    }
}