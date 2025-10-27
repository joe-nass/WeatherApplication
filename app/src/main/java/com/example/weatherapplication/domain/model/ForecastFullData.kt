package com.example.weatherapplication.domain.model

import com.example.weatherapplication.domain.model.current.Current


data class ForecastFullData (
    val location: Location,
    val current: Current,
    val forecast: Forecast,
)