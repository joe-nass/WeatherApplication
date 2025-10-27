package com.example.weatherapplication.data.data_sources.remote

import com.example.weatherapplication.data.dto.CurrentDto
import com.example.weatherapplication.data.dto.ForecastDto
import com.example.weatherapplication.data.dto.LocationDto


data class ForecastResponse(
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto,
)
