package com.example.weatherapplication.features.weather.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ForecastSection(modifier: Modifier = Modifier, scrollState: ScrollState) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        Text(
            "TEST", modifier = Modifier
                .align(Alignment.Center)
                .height(height = 1200.dp)
        )
    }
}