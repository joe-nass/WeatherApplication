package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.Location
import com.google.gson.annotations.SerializedName

data class LocationDto(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    @SerializedName("tz_id")
    val tzId: String,
    val localtime: String,
)

fun LocationDto.toDomain () = Location(
    name = name,
    region = region,
    country = country,
    lat = lat,
    lon = lon,
    tzId = tzId,
    localtime = localtime,
)