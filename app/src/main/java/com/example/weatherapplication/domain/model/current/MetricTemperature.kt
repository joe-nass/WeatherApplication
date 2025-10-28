package com.example.weatherapplication.domain.model.current

data class MetricTemperature(
    override val temp: Int,
    override val feelsLike: Int
) : Temperature