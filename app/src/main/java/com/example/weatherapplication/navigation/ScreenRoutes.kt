package com.example.weatherapp.navigation

import kotlinx.serialization.Serializable

sealed class ScreenRoutes {

    @Serializable
    object CurrentWeather : ScreenRoutes()

}