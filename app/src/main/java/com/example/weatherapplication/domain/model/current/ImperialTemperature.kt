package com.example.weatherapplication.domain.model.current

data class ImperialTemperature(
    override val temp: Double,
    override val feelsLike: Double
) : Temperature