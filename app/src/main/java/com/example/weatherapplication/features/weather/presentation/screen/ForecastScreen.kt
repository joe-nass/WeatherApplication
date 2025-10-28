package com.example.weatherapplication.features.weather.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weatherapplication.R
import com.example.weatherapplication.core.components.ErrorStateUi
import com.example.weatherapplication.core.components.LoadingStateUi
import com.example.weatherapplication.domain.ForecastFullData
import com.example.weatherapplication.features.weather.ForecastContract
import com.example.weatherapplication.features.weather.presentation.components.CurrentTempSection
import com.example.weatherapplication.features.weather.presentation.components.ForecastSection


@Composable
fun ForecastScreen(uiState: ForecastContract.ForecastUiState) {

    val scrollState = rememberScrollState()


    Scaffold(bottomBar = {}) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.bg_night), contentDescription = "bg"
            )
            when {
                uiState.error != null -> {
                    ErrorStateUi(msg = uiState.error)
                }

                uiState.isLoading -> {
                    LoadingStateUi()
                }

                uiState.data != null && !uiState.isLoading -> {
                    SuccessStateUi(
                        forecast = uiState.data,
                        scrollState = scrollState
                    )
                }
            }
        }

    }
}



@Composable
fun SuccessStateUi(scrollState: ScrollState, forecast: ForecastFullData) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)

        ) {
            CurrentTempSection(
                location = forecast.location,
                current = forecast.current,
                forecastDay = forecast.forecast.forecastDay.first(),
                scrollState = scrollState
            )

            ForecastSection(
                scrollState = scrollState,
//                forecast = forecast
            )
        }

    }
}

