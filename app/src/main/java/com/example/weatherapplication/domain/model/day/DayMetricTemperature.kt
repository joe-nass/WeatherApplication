package com.example.weatherapplication.domain.model.day

data class DayMetricTemperature(
    override val maxTemp: Double,
    override val minTemp: Double,
    override val avgTemp: Double
) : DayTemperature