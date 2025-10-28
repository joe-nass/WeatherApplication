package com.example.weatherapplication.features.weather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

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
    Scaffold(topBar = {}, bottomBar = {}) {
        when(uiState){
//            is uiState.Error -> {
//                Text(uiState.error)
//            }
//            is ForecastContract.ForecastUiState.Loading -> {
//                Text("Loading")
//            }
//            is ForecastContract.ForecastUiState.Success -> {
//                Text(uiState.data.toString())
//            }
        }
        Text("Home", modifier = Modifier
            .fillMaxSize()
            .padding(it))
    }
}