package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.Astro

data class AstroDto(
    val sunrise: String,
    val sunset: String,
)

fun AstroDto.toDomain () = Astro(
    sunrise = sunrise,
    sunset = sunset
)
