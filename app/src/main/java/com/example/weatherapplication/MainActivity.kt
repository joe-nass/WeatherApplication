package com.example.weatherapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.navigation.ScreenRoutes
import com.example.weatherapplication.core.theme.WeatherApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApplicationTheme {
                WeatherApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherApplicationTheme {
        WeatherApp()
    }
}

@Composable
fun WeatherApp() {


    val navController = rememberNavController()

    NavHost(navController, startDestination = ScreenRoutes.Home){
        composable<ScreenRoutes.Home>{
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(topBar = {}, bottomBar = {}) {
        Text("Home", modifier = Modifier.fillMaxSize().padding(it))
    }
}