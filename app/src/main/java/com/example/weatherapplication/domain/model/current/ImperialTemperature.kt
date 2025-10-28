package com.example.weatherapplication.domain.model.current

data class ImperialTemperature(
    override val temp: Int,
    override val feelsLike: Int
) : Temperature