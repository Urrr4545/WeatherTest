package com.urrr4545.weathertest.data.response

import com.urrr4545.weathertest.data.WeatherApi
import com.urrr4545.weathertest.domain.mapper.DataMapper
import com.urrr4545.weathertest.domain.model.GeoInfo
import com.urrr4545.weathertest.domain.model.WeatherInfo
import com.urrr4545.weathertest.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dataMapper: DataMapper
) : WeatherRepository {
    override suspend fun getForecastDaily(geoInfo: GeoInfo): WeatherInfo {
        return dataMapper.mapToWeatherModel(api.getForecastDaily(geoInfo.lat, geoInfo.lon))
    }

    override suspend fun getCityGeo(city: String): GeoInfo? {
        return dataMapper.mapToGeoModel(api.getCityGeoInfo(city))
    }
}