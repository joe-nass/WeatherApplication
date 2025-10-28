package com.example.weatherapplication.domain.model.weatherInfo

import com.example.weatherapplication.domain.model.current.Temperature

class MetricUnits(
    val windKph: Double,
    val pressureMb: Double,
    val precipMm: Double,
    val temperature: Temperature
)