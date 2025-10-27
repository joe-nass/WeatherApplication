package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.AirQuality
import com.example.weatherapplication.domain.model.Condition
import com.example.weatherapplication.domain.model.Current
import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Long,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("is_day")
    val isDay: Long,
    val condition: ConditionDto,
    @SerializedName("wind_mph")
    val windMph: Double,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_degree")
    val windDegree: Long,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_in")
    val pressureIn: Double,
    @SerializedName("precip_mm")
    val precipMm: Long,
    @SerializedName("precip_in")
    val precipIn: Long,
    val humidity: Long,
    val cloud: Long,
    @SerializedName("feelslike_c")
    val feelsLikeC: Long,
    @SerializedName("feelslike_f")
    val feelsLikeF: Double,
    val uv: Double,
    @SerializedName("ait_quality")
    val airQuality: AirQualityDto?,
)

fun CurrentDto.toDomain () = Current(
    lastUpdated = lastUpdated,
    tempC = tempC,
    tempF = tempF,
    isDay = isDay,
    condition = condition.toDomain(),
    windMph = windMph,
    windKph = windKph,
    windDegree = windDegree,
    windDir = windDir,
    pressureIn = pressureIn,
    precipMm = precipMm,
    precipIn = precipIn,
    humidity = humidity,
    cloud = cloud,
    feelsLikeC = feelsLikeC,
    feelsLikeF = feelsLikeF,
    uv = uv,
    airQuality = airQuality?.toDomain(),
)