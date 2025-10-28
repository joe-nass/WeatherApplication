package com.example.weatherapplication.domain.model.day

data class DayMetricTemperature(
    override val maxTemp: Int,
    override val minTemp: Int,
    override val avgTemp: Int
) : DayTemperature