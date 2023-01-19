package com.urrr4545.weathertest.data

import com.urrr4545.weathertest.Constants
import com.urrr4545.weathertest.data.response.DailyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeahterApi {
    @GET("forecast/daily")
    suspend fun getForecastDaily(
        @Query("q") cityName: String,
        @Query("cnt") cnt: Int? = Constants.WEATHER_COUNT,
        @Query("units") units: String? = Constants.WEATHER_TEMP_METRIC,
        @Query("appid") appid: String? = Constants.WEATHER_API_KEY
    ): DailyResponse
}