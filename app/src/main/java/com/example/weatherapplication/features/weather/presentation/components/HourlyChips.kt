package com.example.weatherapplication.features.weather.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.core.components.NetworkImage
import com.example.weatherapplication.core.extentions.extractHoursWith12System
import com.example.weatherapplication.domain.model.Hour
import com.example.weatherapplication.domain.model.Condition
import com.example.weatherapplication.domain.model.weatherInfo.UnitType
import com.example.weatherapplication.domain.model.weatherInfo.ImperialUnits
import com.example.weatherapplication.domain.model.weatherInfo.MetricUnits
import com.example.weatherapplication.domain.model.current.ImperialTemperature
import com.example.weatherapplication.domain.model.current.MetricTemperature

@Composable
fun HourlyCard(hour: Hour) {

    val extractedHours = hour.time.extractHoursWith12System()


    Surface(shape = CircleShape) {
        Column(
            modifier = Modifier
                .height(146.dp)
                .width(60.dp)
                .padding(vertical = 15.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(extractedHours)
            //TODO: replace with mapped icons later
            NetworkImage(
                url = hour.condition.icon,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(50.dp)
            )
            Text(hour.unitType.metric.temperature.temp.toString() + "\u2103")
        }
    }
}


@Preview
@Composable
private fun PreviewHourlyCard() {
    HourlyCard(
        hour = Hour(
            time = "2025-10-29 00:00",
            unitType = UnitType(
                imperial = ImperialUnits(
                    windMph = 7.4,
                    pressureIn = 29.72,
                    precipIn = 0.0,
                    temperature = ImperialTemperature(temp = 51, feelsLike = 48)
                ),
                metric = MetricUnits(
                    windKph = 11.9,
                    pressureMb = 1006.0,
                    precipMm = 0.0,
                    temperature = MetricTemperature(temp = 11, feelsLike = 9)
                )
            ),
            isDay = 0,
            condition = Condition(
                code = 1003,
                icon = "//cdn.weatherapi.com/weather/64x64/night/116.png",
                text = "Partly Cloudy "
            ),
            windDegree = 217.0,
            windDir = "SW",
            humidity = 74,
            cloud = 51,
            willItRain = 0,
            chanceOfRain = 0.0,
            willItSnow = 0,
            chanceOfSnow = 0.0,
            uv = 0.0,
        )
    )
}