package com.urrr4545.weathertest.domain.model

data class WeatherInfo(
    val city: String,
    val dt: Long,
    val weathers: List<Weather>
)

data class Weather(
    val time: String,
    val min: String,
    val max: String,
    val main: String,
    val icon: String,
)