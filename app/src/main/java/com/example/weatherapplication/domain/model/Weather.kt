package com.example.weatherapplication.domain.model

import com.example.weatherapplication.domain.model.current.Current

data class Weather(
    val current: Current,
    val location: Location
)