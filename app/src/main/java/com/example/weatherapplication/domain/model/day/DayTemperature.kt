package com.example.weatherapplication.domain.model.day

sealed interface DayTemperature {
    val maxTemp: Double
    val minTemp: Double
    val avgTemp: Double
}