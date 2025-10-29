package com.example.weatherapplication.features.weather.presentation.components.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.R
import com.example.weatherapplication.core.components.VSpacer
import com.example.weatherapplication.core.components.WeatherCardTemplate
import com.example.weatherapplication.domain.model.AirQuality
import com.example.weatherapplication.domain.model.Astro
import com.example.weatherapplication.domain.model.Hour
import com.example.weatherapplication.domain.model.current.Current
import com.example.weatherapplication.features.weather.presentation.components.HourlyCard

@Composable
fun TodayTab(modifier: Modifier = Modifier, hours: List<Hour>, current: Current, astro: Astro) {
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(TodayTabValues.chipsSpace),
            contentPadding = TodayTabValues.chipsPadding,
            modifier = modifier.padding(vertical = 20.dp)
        ) {
            items(hours) { hour -> HourlyCard(hour = hour) }
        }

        AirQualityCard(current.airQuality)

        VSpacer(12.dp)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            userScrollEnabled = false,
            modifier = modifier
                .padding(TodayTabValues.contentPadding)
                .heightIn(min = 0.dp, max = 1024.dp)
        ) {
            item { UvCard(current) }
            item { SunriseCard(astro) }
            item { WindCard(current) }
        }
    }
}

@Composable
fun AirQualityCard(airQuality: AirQuality?) {
    airQuality?.let {
        WeatherCardTemplate(
            title = "Air Quality",
            icon = Icons.Default.Equalizer,
            data = airQuality,
            isInGrid = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(TodayTabValues.contentPadding),
            height = 200.dp
        ) { airQuality ->
            var expanded by remember { mutableStateOf(false) }
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    airQuality.usEpaIndex.toString() + " - " + (airQuality.airQualityIndex)
                )
                Text("Slider here")
                HorizontalDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(if (expanded) "See less" else "See more")
                    Icon(
                        if (expanded) Icons.Default.ExpandMore else Icons.Default.ExpandLess,
                        contentDescription = "See less/more"
                    )
                }

                if (expanded) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        AirQualityDataItem("CO:", airQuality.co)
                        AirQualityDataItem("NO2:", airQuality.no2)
                        AirQualityDataItem("O3:", airQuality.o3)
                        AirQualityDataItem("PM2.5:", airQuality.pm2_5)
                        AirQualityDataItem("PM10:", airQuality.pm10)
                        AirQualityDataItem("SO2:", airQuality.so2)
                    }
                }
            }
        }
    }
}

@Composable
fun UvCard(current: Current) {
    WeatherCardTemplate(
        title = "UV Index",
        icon = Icons.Default.WbSunny,
        data = current,
        modifier = Modifier.fillMaxWidth(),
    ) { current -> }
}

@Composable
fun SunriseCard(astro: Astro) {
    WeatherCardTemplate(
        title = "Sunrise",
        icon = Icons.Default.WbTwilight,
        data = astro,
        modifier = Modifier.fillMaxWidth(),
    ) { astro ->
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Text(astro.sunrise)
            Text("Slider Here")
            Text("Sunset: ${astro.sunset}")
        }
    }
}

@Composable
fun WindCard(current: Current) {
    WeatherCardTemplate(
        title = "Wind",
        icon = Icons.Default.Air,
        data = current,
    ) { current ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.compas),
                contentScale = ContentScale.Fit,
                contentDescription = "WindCompas"
            )
            Image(
                modifier = Modifier.rotate(current.windDegree.toFloat()),
                painter = painterResource(R.drawable.compas_arrow),
                contentScale = ContentScale.Fit,
                contentDescription = "WindDirection"
            )
        }
    }
}

@Composable
private fun AirQualityDataItem(text: String, value: Double, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text)
        Text(value.toString())
    }
}

private data object TodayTabValues {
    val chipsSpace: Dp = 10.dp
    val chipsPadding = PaddingValues(horizontal = 20.dp)
    val contentPadding = PaddingValues(horizontal = 24.dp)
}
