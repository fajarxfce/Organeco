package com.organeco.model.builder

data class CalculatorRequest(
    val temperature: Int,
    val humidity: Int,
    val moisture: Int,
    val soilType: String,
    val cropType: String,
    val nitrogen: Int,
    val potassium: Int,
    val phosphorous: Int
)