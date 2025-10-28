package com.example.weatherapplication.data.data_sources.remote

import com.example.weatherapplication.data.dto.CurrentDto
import com.example.weatherapplication.data.dto.ForecastDto
import com.example.weatherapplication.data.dto.LocationDto
import com.example.weatherapplication.domain.model.Forecast
import com.example.weatherapplication.domain.model.Location
import com.example.weatherapplication.domain.model.current.Current

data class ForecastResponse (
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto,
)