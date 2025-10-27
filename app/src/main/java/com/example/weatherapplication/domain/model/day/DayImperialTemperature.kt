package com.example.weatherapplication.domain.model.day

data class DayImperialTemperature(
    override val maxTemp: Double,
    override val minTemp: Double,
    override val avgTemp: Double
) : DayTemperature