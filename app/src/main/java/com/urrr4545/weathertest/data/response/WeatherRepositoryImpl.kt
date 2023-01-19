package com.urrr4545.weathertest.data.response

import com.urrr4545.weathertest.data.WeatherApi
import com.urrr4545.weathertest.domain.mapper.DataMapper
import com.urrr4545.weathertest.domain.model.WeatherInfo
import com.urrr4545.weathertest.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dataMapper: DataMapper
): WeatherRepository{
    override suspend fun getForecastDaily(city: String): WeatherInfo {
        return dataMapper.mapToDomainModel(api.getForecastDaily(city))
    }
}