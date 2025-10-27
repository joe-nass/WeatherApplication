package com.example.weatherapplication.domain.model

import com.google.gson.annotations.SerializedName



data class Hour(
    val time: String,
    val tempC: Double,
    val tempF: Double,
    val isDay: Long,
    val condition: Condition,
    val windMph: Double,
    val windKph: Double,
    val windDegree: Long,
    val windDir: String,
    val pressureMb: Long,
    val pressureIn: Double,
    val precipMm: Double,
    val precipIn: Double,
    val humidity: Long,
    val cloud: Long,
    val feelsLikeC: Double,
    val feelsLikeF: Double,
    val willItRain: Long,
    val chanceOfRain: Long,
    val willItSnow: Long,
    val chanceOfSnow: Long,
    val uv: Double,
)
