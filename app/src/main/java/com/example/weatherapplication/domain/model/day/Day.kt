package com.example.weatherapplication.domain.model.day

import com.example.weatherapplication.domain.model.AirQuality
import com.example.weatherapplication.domain.model.Condition

data class Day(
    val unitTypeDayTemperature: UnitTypeDayTemperature,
    val condition: Condition,
    val airQuality: AirQuality?
)
