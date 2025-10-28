package com.example.weatherapplication.features.weather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collect

@Composable
fun CurrentWeatherScreen(viewModel: CurrentWeatherViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        when(viewModel.events.collect()){
            is ForecastContract.ForecastEvent.ShowMessage -> {

            }
            is ForecastContract.ForecastEvent.NavigateToSearch -> {

            }
        }
    }

    Scaffold(topBar = {}, bottomBar = {}) {
        Text("Home", modifier = Modifier.fillMaxSize().padding(it))
    }
}