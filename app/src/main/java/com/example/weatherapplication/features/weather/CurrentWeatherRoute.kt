package com.example.weatherapplication.features.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapplication.R
import com.example.weatherapplication.core.components.ErrorStateUi
import com.example.weatherapplication.core.components.HSpacer
import com.example.weatherapplication.core.components.LoadingStateUi
import com.example.weatherapplication.domain.ForecastFullData
import com.example.weatherapplication.domain.model.Condition
import com.example.weatherapplication.domain.model.ForecastDay
import com.example.weatherapplication.domain.model.Location
import com.example.weatherapplication.domain.model.current.Current
import com.example.weatherapplication.domain.model.weatherInfo.UnitType
import com.example.weatherapplication.features.weather.presentation.screen.ForecastScreen

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
