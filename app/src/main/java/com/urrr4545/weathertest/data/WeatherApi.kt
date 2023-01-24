package com.urrr4545.weathertest.data

import com.urrr4545.weathertest.Constants
import com.urrr4545.weathertest.data.dto.GeoInfoDto
import com.urrr4545.weathertest.data.response.DailyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //todo 무료로 제공하는 key로 일별 api를 제공하지 않음. 해서 3시간씩 5일치로 mapping하여 사용해야함
    @GET("data/2.5/forecast")
    suspend fun getForecastDaily(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String? = Constants.WEATHER_TEMP_METRIC,
        @Query("appid") appid: String? = Constants.WEATHER_API_KEY
    ): DailyResponse

    @GET("geo/1.0/direct")
    suspend fun getCityGeoInfo(
        @Query("q") cityName: String,
        @Query("limit") limit: Int? = Constants.GEO_COUNT,
        @Query("appid") appid: String? = Constants.WEATHER_API_KEY
    ): List<GeoInfoDto>
}