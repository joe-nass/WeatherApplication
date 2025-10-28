package com.example.weatherapplication.domain.model.current

sealed interface Temperature {
    val temp: Double
    val feelsLike: Double
}