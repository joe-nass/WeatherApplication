package com.example.weatherapplication.domain.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    val forecastDay: List<ForecastDay>,
)


