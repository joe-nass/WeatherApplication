package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.AirQuality
import com.example.weatherapplication.domain.model.day.DayMetricTemperature
import com.example.weatherapplication.domain.model.day.Day
import com.example.weatherapplication.domain.model.day.DayImperialTemperature
import com.example.weatherapplication.domain.model.day.UnitTypeDayTemperature
import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c")
    val maxTempC: Double,
    @SerializedName("maxtemp_f")
    val maxTempF: Double,
    @SerializedName("mintemp_c")
    val minTempC: Double,
    @SerializedName("mintemp_f")
    val minTempF: Double,
    @SerializedName("avgtemp_c")
    val avgTempC: Double,
    @SerializedName("avgtemp_f")
    val avgTempF: Double,
    val condition: ConditionDto,
    @SerializedName("air_quality")
    val airQuality: AirQualityDto?
)

fun DayDto.toDomain() = Day(
    unitTypeDayTemperature = UnitTypeDayTemperature(
        imperial = DayImperialTemperature(
            maxTempF,
            minTempF,
            avgTempF
        ),
        metric = DayMetricTemperature(
            maxTempC,
            minTempC,
            avgTempC
        )
    ),
    condition = condition.toDomain(),
    airQuality = airQuality?.toDomain()
)