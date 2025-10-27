package com.example.weatherapplication.domain.model

import com.google.gson.annotations.SerializedName

data class Day(
    val maxTempC: Double,
    val maxTempF: Double,
    val minTempC: Double,
    val minTempF: Double,
    val avgTempC: Double,
    val avgTempF: Double,
    val condition: Condition,
)