package com.urrr4545.weathertest.domain.model

data class Weather(
    val id: Int,
    val date: String,
    val dateStr: String,
    val min: String,
    val max: String,
    val main: String,
    val icon: String,
    val cityName: String
)