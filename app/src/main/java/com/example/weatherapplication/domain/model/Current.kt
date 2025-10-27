package com.example.weatherapplication.domain.model

import com.google.gson.annotations.SerializedName

data class Current(
    val lastUpdated: String,
    val tempC: Long,
    val tempF: Double,
    val isDay: Long,
    val condition: Condition,
    val windMph: Double,
    val windKph: Double,
    val windDegree: Long,
    val windDir: String,
    val pressureIn: Double,
    val precipMm: Long,
    val precipIn: Long,
    val humidity: Long,
    val cloud: Long,
    val feelsLikeC: Long,
    val feelsLikeF: Double,
    val uv: Double,
    val airQuality: AirQuality?,
)
