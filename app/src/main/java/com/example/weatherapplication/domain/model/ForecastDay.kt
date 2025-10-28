package com.example.weatherapplication.domain.model

import com.example.weatherapplication.domain.model.day.Day

data class ForecastDay(
    val date: String,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>,
)