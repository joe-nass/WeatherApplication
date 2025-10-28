package com.example.weatherapplication.domain.model.current

data class MetricTemperature(
    override val temp: Double,
    override val feelsLike: Double
) : Temperature