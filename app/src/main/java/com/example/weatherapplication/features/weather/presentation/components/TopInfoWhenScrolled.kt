package com.example.weatherapplication.features.weather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.weatherapplication.domain.model.Condition
import com.example.weatherapplication.domain.model.Location
import com.example.weatherapplication.domain.model.weatherInfo.UnitType

@Composable
fun TopInfoWhenScrolled(country: Location, temp: UnitType, condition: Condition) {
    Column(Modifier.fillMaxWidth().background(Color.Yellow), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(country.name )
        Text("${temp.metric.temperature.temp}\u2103 | ${condition.text}")
    }
}