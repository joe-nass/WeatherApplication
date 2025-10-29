package com.example.weatherapplication.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> WeatherCardTemplate(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    data: T,
    height: Dp = 140.dp,
    isInGrid: Boolean = true,
    body: @Composable (T) -> Unit,
) {
    val shape = RoundedCornerShape(24.dp)

    val cardModifier = if (isInGrid) {
        modifier.aspectRatio(1f)
    } else {
        modifier.wrapContentHeight()
    }
//creamy ranch & smocked turkey
    Column(
        modifier = cardModifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = shape
            )
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = icon,
                contentDescription = title
            )
            HSpacer(8.dp)
            Text(title)
        }
        VSpacer(8.dp)
        body(data)
    }
}


@Preview(heightDp = 100, widthDp = 100, showBackground = true)
@Composable
private fun PrevWeatherCard() {
    WeatherCardTemplate<String>(
        title = "Weather",
        icon = Icons.Default.Cloud,
        data = "body",
        body = { data ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(data)
                VSpacer(8.dp)
                Text("$data + 2")
            }
        }
    )
}