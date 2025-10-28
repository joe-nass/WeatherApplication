package com.example.weatherapplication.features.weather.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapplication.core.components.HSpacer
import com.example.weatherapplication.domain.model.ForecastDay
import com.example.weatherapplication.domain.model.Location
import com.example.weatherapplication.domain.model.current.Current


@Composable
fun CurrentTempSection(
    modifier: Modifier = Modifier,
    location: Location,
    current: Current,
    forecastDay: ForecastDay,
    scrollState: ScrollState
) {

    val style = TextStyle(
        color = Color.White,
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold
    )

    //TODO: Hide when scrolling
    TopInfoWhenScrolled(
        country = location,
        temp = current.unitType,
        condition = current.condition,
    )

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = location.name, style = style)
        Text(
            text = current.unitType.metric.temperature.temp.toString() + "\u00B0C",
            style = style.copy(
                fontSize = 122.sp,
                fontWeight = FontWeight.Light
            )
        )
        Text(text = current.condition.text, style = style)
        Row {
            Text(
                text = "H:" + forecastDay.day.unitTypeDayTemperature.metric.maxTemp.toString() + "\u00B0C",
                style = style.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light
                )
            )
            HSpacer(12.dp)
            Text(
                text = "L:" + forecastDay.day.unitTypeDayTemperature.metric.minTemp.toString() + "\u00B0C",
                style = style.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light
                )
            )

        }
    }
}