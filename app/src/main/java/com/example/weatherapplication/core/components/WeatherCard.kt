package com.example.weatherapplication.core.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapplication.R

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    title: String,
    iconRes: Int,
    body: @Composable () -> Unit,
) {
    val shape =  RoundedCornerShape(8.dp)
    Column(modifier = modifier
        .background(
            color = MaterialTheme.colorScheme.surface,
            shape = shape
        )
        .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.onSurface,
        shape = shape)
        .padding(8.dp)
    )
    {
        Row (verticalAlignment = Alignment.CenterVertically){
            Image( modifier = Modifier.size(24.dp), painter = painterResource(iconRes), contentDescription = title)
            HSpacer(8.dp)
            Text(title)
        }
        VSpacer(8.dp)
        Box (modifier = Modifier.weight(1f)) {
            body()
        }
    }
}

@Preview(heightDp = 100, widthDp = 100, showBackground = true, )
@Composable
private fun PrevWeatherCard() {
    WeatherCard(
        title = "Weather",
        iconRes = R.drawable.ic_launcher_background,
        body = {
            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Text("Body")
                VSpacer(8.dp)
                Text("Body")
            }
        }
    )
}