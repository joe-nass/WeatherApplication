package com.example.weatherapplication.domain.model.day

sealed interface DayTemperature {
    val maxTemp: Int
    val minTemp: Int
    val avgTemp: Int
}