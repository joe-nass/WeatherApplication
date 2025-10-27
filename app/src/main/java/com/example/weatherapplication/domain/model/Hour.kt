package com.example.weatherapplication.domain.model

import com.example.weatherapplication.domain.model.weatherInfo.UnitType

data class Hour(
    val time: String,
    val unitType: UnitType,
    val isDay: Int,
    val condition: Condition,
    val windDegree: Double,
    val windDir: String,
    val humidity: Int,
    val cloud: Int,
    val willItRain: Int,
    val chanceOfRain: Double,
    val willItSnow: Int,
    val chanceOfSnow: Double,
    val uv: Double,
)
