package com.urrr4545.weathertest.domain.usecase

import com.urrr4545.weathertest.domain.mapper.DataMapper
import com.urrr4545.weathertest.domain.model.GeoInfo
import com.urrr4545.weathertest.domain.model.WeatherInfo
import com.urrr4545.weathertest.domain.repository.WeatherRepository
import com.urrr4545.weathertest.domain.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherUseCase
@Inject
constructor(
    private val weatherRepository: WeatherRepository,
    private val dataMapper: DataMapper
) {

    suspend fun getGeoInfo(city: String): Flow<OperationResult<GeoInfo>> = flow {
        try {
            emit(OperationResult.Loading())
            val geoItem = weatherRepository.getCityGeo(city)
            geoItem?.let {
                emit(OperationResult.Success(geoItem))
            } ?: kotlin.run {
                emit(OperationResult.Error("GeoInfo is Null"))
            }

        } catch (e: Exception) {
            emit(OperationResult.Error("${e.message}"))
        }
    }

    suspend fun getWeatherInfo(geoInfo: GeoInfo?): Flow<OperationResult<WeatherInfo>> = flow {
        try {
            emit(OperationResult.Loading())
            if (geoInfo == null) {
                emit(OperationResult.Error("failed call Weahter, cause geo info null"))
            } else {
                val listItem = weatherRepository.getForecastDaily(geoInfo)
                emit(OperationResult.Success(listItem))
            }
        } catch (e: Exception) {
            emit(OperationResult.Error("${e.message}"))
        }
    }

}