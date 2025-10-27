package com.example.weatherapplication.domain.model.current

import com.example.weatherapplication.domain.model.AirQuality
import com.example.weatherapplication.domain.model.Condition
import com.example.weatherapplication.domain.model.weatherInfo.UnitType

data class Current(
    val lastUpdated: String,
    val unitType: UnitType,
    val isDay: Int,
    val condition: Condition,
    val windDegree: Int,
    val windDir: String,
    val humidity: Int,
    val cloud: Int,
    val uv: Int,
    val airQuality: AirQuality?,
)

