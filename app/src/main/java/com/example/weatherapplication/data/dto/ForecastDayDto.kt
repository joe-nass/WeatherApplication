package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.ForecastDay

data class ForecastDayDto(
    val date: String,
    val day: DayDto,
    val astro: AstroDto,
    val hour: List<HourDto>,
)

fun ForecastDayDto.toDomain () = ForecastDay(
    date = date,
    day = day.toDomain(),
    astro = astro.toDomain(),
    hour = hour.map { it.toDomain() }
)