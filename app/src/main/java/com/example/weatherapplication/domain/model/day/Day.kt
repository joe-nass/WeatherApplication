package com.example.weatherapplication.domain.model.day

import com.example.weatherapplication.domain.model.Condition

data class Day(
    val celsiusTemperature: DayTemperature,
    val fahrenheitTemperature: DayTemperature,
    val condition: Condition
)