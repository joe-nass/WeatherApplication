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
    val uv: Double,
    val airQuality: AirQuality?,
){
    val uvStatus: String = when(uv) {
        in 0.0..2.0 -> "Low"
        in 3.0..5.0 -> "Moderate"
        in 6.0..7.0 -> "High"
        in 8.0..10.0 -> "Very High"
        else -> "Extreme"
    }
}

