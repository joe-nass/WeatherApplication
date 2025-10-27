package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.Condition
import com.example.weatherapplication.domain.model.Day
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
)

fun DayDto.toDomain () = Day(
    maxTempC = maxTempC,
    maxTempF = maxTempF,
    minTempC = minTempC,
    minTempF = minTempF,
    avgTempC = avgTempC,
    avgTempF = avgTempF,
    condition = condition.toDomain()
)