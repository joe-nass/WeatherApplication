package com.example.weatherapplication.domain.model.current

sealed interface Temperature {
    val temp: Int
    val feelsLike: Int
}