package com.urrr4545.weathertest.domain.repository

import com.urrr4545.weathertest.domain.model.GeoInfo
import com.urrr4545.weathertest.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getForecastDaily(geoInfo: GeoInfo): WeatherInfo
    suspend fun getCityGeo(city: String): GeoInfo?
}