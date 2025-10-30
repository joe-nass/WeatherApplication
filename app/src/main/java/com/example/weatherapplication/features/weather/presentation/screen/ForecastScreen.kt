package com.example.weatherapplication.features.weather.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weatherapplication.R
import com.example.weatherapplication.core.components.ErrorStateUi
import com.example.weatherapplication.core.components.LoadingStateUi
import com.example.weatherapplication.domain.model.ForecastFullData
import com.example.weatherapplication.features.weather.ForecastContract
import com.example.weatherapplication.features.weather.presentation.components.CurrentTempSection
import com.example.weatherapplication.features.weather.presentation.components.ForecastSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen(uiState: ForecastContract.ForecastUiState, snackBarHostState: SnackbarHostState, onRefresh: () -> Unit, onErrorRetry: () -> Unit) {

    val scrollState = rememberScrollState()

    Scaffold(bottomBar = {}, snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) { paddingValues ->
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
                    ErrorStateUi(msg = uiState.error, onRetry = onErrorRetry)
                }

                uiState.isLoading -> {
                    LoadingStateUi()
                }

                uiState.data != null && !uiState.isLoading -> {
                    SuccessStateUi(
                        forecast = uiState.data,
                        scrollState = scrollState,
                        isRefreshing = uiState.isRefreshing,
                        onRefresh = onRefresh
                    )
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessStateUi(
    scrollState: ScrollState,
    forecast: ForecastFullData,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        state = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                CurrentTempSection(
                    location = forecast.location,
                    current = forecast.current,
                    forecastDay = forecast.forecast.forecastDay.first(),
                    scrollState = scrollState
                )
            }
            item {
                ForecastSection(
                    forecastDay = forecast.forecast.forecastDay.first(),
                    current = forecast.current
                )
            }
        }
    }
}
