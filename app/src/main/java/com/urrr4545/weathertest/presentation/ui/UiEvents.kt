package com.urrr4545.weathertest.presentation.ui

import com.urrr4545.weathertest.domain.model.GeoInfo

sealed class UiEvents {

    data class GetGeoInfo(val city: String, val orderIndex: Int) : UiEvents()
    data class GetWeather(val geoInfo: GeoInfo, val orderIndex: Int) : UiEvents()
}
