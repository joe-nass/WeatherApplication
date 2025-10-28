package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.Forecast
import com.example.weatherapplication.domain.model.ForecastDay
import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDayDto>,
)

fun ForecastDto.toDomain () = Forecast(
    forecastDay = forecastDay.map { it.toDomain() }
)