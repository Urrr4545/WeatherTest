package com.urrr4545.weathertest.data.response

import com.google.gson.annotations.SerializedName
import com.urrr4545.weathertest.data.dto.CityDto
import com.urrr4545.weathertest.data.dto.WeatherInfoDto

data class DailyResponse(
    @SerializedName("city")
    val city: CityDto,

    @SerializedName("cnt")
    val cnt: Int, //API 응답에 반환된 일 수

    @SerializedName("list")
    val list: List<WeatherInfoDto>
)
