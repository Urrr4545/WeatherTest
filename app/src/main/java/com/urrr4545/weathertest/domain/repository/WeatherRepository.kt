package com.urrr4545.weathertest.domain.repository

import com.urrr4545.weathertest.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getForecastDaily(city: String): WeatherInfo
}