package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.current.Current
import com.example.weatherapplication.domain.model.current.ImperialTemperature
import com.example.weatherapplication.domain.model.current.MetricTemperature
import com.example.weatherapplication.domain.model.weatherInfo.ImperialUnits
import com.example.weatherapplication.domain.model.weatherInfo.MetricUnits
import com.example.weatherapplication.domain.model.weatherInfo.UnitType
import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Int,
    val condition: ConditionDto,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("pressure_mb") val pressureMb: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("precip_in") val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("feelslike_f") val feelsLikeF: Double,
    val uv: Int,
    @SerializedName("ait_quality") val airQuality: AirQualityDto?,
)

fun CurrentDto.toDomain() = Current(
    lastUpdated = lastUpdated,
    unitType = UnitType(
        imperial = ImperialUnits(windMph, pressureIn, precipIn, ImperialTemperature(tempF, feelsLikeF)),
        metric = MetricUnits(windKph, pressureMb, precipMm, MetricTemperature(tempC, feelsLikeC))
    ),
    isDay = isDay,
    condition = condition.toDomain(),
    windDegree = windDegree,
    windDir = windDir,
    humidity = humidity,
    cloud = cloud,
    uv = uv,
    airQuality = airQuality?.toDomain(),
)