package com.example.weatherapplication.core.extentions

import java.text.SimpleDateFormat
import java.util.Locale

fun String.to12HourTime(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        this // return original string if parsing fails
    }
}

fun String.extractHoursWith12System(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("hh a", Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        this // return original string if parsing fails
    }
}