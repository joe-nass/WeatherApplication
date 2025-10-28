package com.example.weatherapplication.domain

import com.example.weatherapplication.domain.model.Forecast
import com.example.weatherapplication.domain.model.Location
import com.example.weatherapplication.domain.model.current.Current

data class ForecastFullData(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)
