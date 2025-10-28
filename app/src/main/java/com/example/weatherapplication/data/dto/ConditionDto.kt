package com.example.weatherapplication.data.dto

import com.example.weatherapplication.domain.model.Condition

data class ConditionDto(
    val text: String,
    val icon: String,
    val code: Int
)

fun ConditionDto.toDomain () = Condition(
    text = text,
    icon = icon,
    code = code
)