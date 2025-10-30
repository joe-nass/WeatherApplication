package com.example.weatherapplication.data.data_sources.remote

import com.example.weatherapplication.data.dto.CurrentDto
import com.example.weatherapplication.data.dto.ForecastDto
import com.example.weatherapplication.data.dto.LocationDto
import com.example.weatherapplication.data.dto.toDomain
import com.example.weatherapplication.domain.model.ForecastFullData

data class ForecastResponse (
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto,
)

fun ForecastResponse.toDomain() = ForecastFullData(
    location = location.toDomain(),
    current = current.toDomain(),
    forecast = forecast.toDomain()
)
