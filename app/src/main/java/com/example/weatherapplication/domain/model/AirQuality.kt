package com.example.weatherapplication.domain.model

data class AirQuality(
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
    val usEpaIndex: Int
){
    val airQualityIndex: String = when (usEpaIndex) {
        1 -> "Good"
        2 -> "Moderate"
        3 -> "Unhealthy for sensitive group"
        4 -> "Unhealthy"
        5 -> "Very Unhealthy"
        6 -> "Hazardous"
        else -> "Unknown"
    }
}