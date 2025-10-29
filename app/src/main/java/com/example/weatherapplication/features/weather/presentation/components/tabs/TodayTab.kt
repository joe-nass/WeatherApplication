package com.example.weatherapplication.features.weather.presentation.components.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.domain.model.Hour
import com.example.weatherapplication.features.weather.presentation.components.HourlyCard

@Composable
fun TodayTab(modifier: Modifier = Modifier, hours: List<Hour>) {

    val chipsSpace = 10.dp

    Column (modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(chipsSpace), contentPadding = PaddingValues(horizontal = chipsSpace), modifier = modifier.padding(vertical = 20.dp)) {
            items(hours)  { hour -> HourlyCard(hour = hour) }
        }
    }
}
