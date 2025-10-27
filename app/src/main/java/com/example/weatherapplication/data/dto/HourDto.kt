package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.Condition
import com.example.weatherapplication.domain.model.Hour
import com.google.gson.annotations.SerializedName


data class HourDto(
    val time: String,
    @SerializedName("temp_c")
    val tempC: Double,
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
    @SerializedName("pressure_mb")
    val pressureMb: Long,
    @SerializedName("pressure_in")
    val pressureIn: Double,
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("precip_in")
    val precipIn: Double,
    val humidity: Long,
    val cloud: Long,
    @SerializedName("feelslike_c")
    val feelsLikeC: Double,
    @SerializedName("feelslike_f")
    val feelsLikeF: Double,
    @SerializedName("will_it_rain")
    val willItRain: Long,
    @SerializedName("chance_of_rain")
    val chanceOfRain: Long,
    @SerializedName("will_it_snow")
    val willItSnow: Long,
    @SerializedName("chance_of_snow")
    val chanceOfSnow: Long,
    val uv: Double,
)

fun HourDto.toDomain () = Hour(
    time = time,
    tempC = tempC,
    tempF = tempF,
    isDay = isDay,
    condition = condition.toDomain(),
    windMph = windMph,
    windKph = windKph,
    windDegree = windDegree,
    windDir = windDir,
    pressureMb = pressureMb,
    pressureIn = pressureIn,
    precipMm = precipMm,
    precipIn = precipIn,
    humidity = humidity,
    cloud = cloud,
    feelsLikeC = feelsLikeC,
    feelsLikeF = feelsLikeF,
    willItRain = willItRain,
    chanceOfRain = chanceOfRain,
    willItSnow = willItSnow,
    chanceOfSnow = chanceOfSnow,
    uv = uv
)
