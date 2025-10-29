package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.AirQuality
import com.google.gson.annotations.SerializedName

data class AirQualityDto (
    val co: Double,
    val no2: Double,
    val o3: Double,
    val pm10: Double,
    val pm2_5: Double,
    val so2: Double,
    /**
     * US - EPA standard.
     * 1 means Good
     * 2 means Moderate
     * 3 means Unhealthy for sensitive group
     * 4 means Unhealthy
     * 5 means Very Unhealthy
     * 6 means Hazardous
     */
    @SerializedName("us-epa-index")
    val usEpaIndex: Int
)

fun AirQualityDto.toDomain () = AirQuality(
    co = co,
    no2 = no2,
    o3 = o3,
    pm10 = pm10,
    pm2_5 = pm2_5,
    so2 = so2,
    usEpaIndex = usEpaIndex
)