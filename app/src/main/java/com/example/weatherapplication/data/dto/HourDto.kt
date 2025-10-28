package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.Hour
import com.example.weatherapplication.domain.model.current.ImperialTemperature
import com.example.weatherapplication.domain.model.current.MetricTemperature
import com.example.weatherapplication.domain.model.weatherInfo.ImperialUnits
import com.example.weatherapplication.domain.model.weatherInfo.MetricUnits
import com.example.weatherapplication.domain.model.weatherInfo.UnitType
import com.google.gson.annotations.SerializedName


data class HourDto(
    val time: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Int,
    val condition: ConditionDto,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_degree") val windDegree: Double,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Double,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("precip_in") val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("feelslike_f") val feelsLikeF: Double,
    @SerializedName("will_it_rain") val willItRain: Int,
    @SerializedName("chance_of_rain") val chanceOfRain: Double,
    @SerializedName("will_it_snow") val willItSnow: Int,
    @SerializedName("chance_of_snow") val chanceOfSnow: Double,
    val uv: Double,
)

fun HourDto.toDomain() = Hour(
    time = time,
    willItRain = willItRain,
    isDay = isDay,
    condition = condition.toDomain(),
    windDegree = windDegree,
    windDir = windDir,
    humidity = humidity,
    cloud = cloud,
    chanceOfRain = chanceOfRain,
    willItSnow = willItSnow,
    chanceOfSnow = chanceOfSnow,
    uv = uv,
    unitType = UnitType(
        imperial = ImperialUnits(
            windMph, pressureIn, precipIn, ImperialTemperature(tempF, feelsLikeF)
        ), metric = MetricUnits(windKph, pressureMb, precipMm, MetricTemperature(tempC, feelsLikeC))
    )
)
