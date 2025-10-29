package com.example.weatherapplication.features.weather.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.domain.model.ForecastDay
import com.example.weatherapplication.domain.model.current.Current
import com.example.weatherapplication.features.weather.presentation.components.tabs.TodayTab
import com.example.weatherapplication.features.weather.presentation.components.tabs.WeekTab


@Composable
fun ForecastSection(modifier: Modifier = Modifier, forecastDay: ForecastDay, current: Current) {

    val shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)

    val tabs = listOf("Today", "Week")
    var selectedTabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.scrollToPage(selectedTabIndex)
    }

    Column(
        modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = 0.5f), shape = shape),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GestureIndicator(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(height = 5.dp, width = 48.dp)
        )

        TabRow(
            selectedTabIndex = 0,
            divider = { TabDivider()},
            indicator = { tabPositions -> TabsIndicator(tabPositions, selectedTabIndex) }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) }
                )
            }

        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .background(Color.Transparent)
                .padding(bottom = 60.dp)
        ) { page ->
            when (page) {
                0 -> TodayTab(hours = forecastDay.hour, current = current, astro = forecastDay.astro)
                1 -> WeekTab()
            }
        }
    }
}

@Composable
fun TabDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black.copy(alpha = 0.5f))
    )
}

@Composable
fun TabsIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    Box(
        modifier = Modifier
            .tabIndicatorOffset(tabPositions[selectedTabIndex])
            .fillMaxWidth()
            .height(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.primaryContainer,
                            Color.Transparent
                        ),
                        startX = 0f,
                        endX = Float.POSITIVE_INFINITY
                    ),
                )
        )
    }
}

@Composable
fun GestureIndicator(modifier: Modifier = Modifier) {
    //TODO: Fix color
    Box(
        modifier
            .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
    )
}