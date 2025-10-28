package com.example.weatherapplication.domain.model

import com.example.weatherapplication.domain.model.current.Current
import com.google.gson.annotations.SerializedName

data class Forecast(
    val forecastDay: List<ForecastDay>,
)


