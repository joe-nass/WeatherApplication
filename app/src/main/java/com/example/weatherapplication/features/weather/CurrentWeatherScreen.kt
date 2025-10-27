package com.example.weatherapplication.features.weather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CurrentWeatherScreen() {
    Scaffold(topBar = {}, bottomBar = {}) {
        Text("Home", modifier = Modifier.fillMaxSize().padding(it))
    }
}